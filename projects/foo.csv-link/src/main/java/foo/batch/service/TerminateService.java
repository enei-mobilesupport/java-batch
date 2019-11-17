package foo.sample.batch.service;

import static foo.sample.constant.devEnums.CancelType.CANCEL;
import static foo.sample.constant.ResultCode.INVALID_USER_STATUS;
import static foo.sample.constant.ResultCode.NG_ALREADY_CANCELED;
import static foo.sample.constant.ResultCode.NG_ALREADY_PENDING;
import static foo.sample.constant.ResultCode.NG_ALREADY_SUBSCRIBED_OTHER;
import static foo.sample.constant.ResultCode.NG_BILLING_HOLD;
import static foo.sample.constant.ResultCode.NG_INTERNAL_ERROR;
import static foo.sample.constant.ResultCode.NG_INVALID_ACCOUNT_STATUS;
import static foo.sample.constant.ResultCode.NG_INVALID_CABLE_ID;
import static foo.sample.constant.ResultCode.NG_RESPONSE;
import static foo.sample.constant.ResultCode.SUCCESS;
import static foo.sample.constant.ResultCode.UNKNOWN;
import static foo.sample.constant.Status.NORMAL;
import static foo.sample.constant.Status.SUSPENDED;
import static foo.sample.constant.Status.TERMINATED;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import foo.sample.app.json.dev.TerminateRequest;
import foo.sample.app.json.dev.TerminateResponse;
import foo.sample.batch.service.base.BaseProvisioningService;
import foo.sample.constant.devEnums.CancelResultCode;
import foo.sample.constant.devEnums.CancelType;
import foo.sample.constant.ResultCode;
import foo.sample.constant.Status;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusKey;

/**
 * 解約のプロビジョニング.
 */
@Service
public class TerminateService extends BaseProvisioningService {

    @Value("${api.dev.endpoint.account.delete}")
    private String terminateUrl;

    // 解約区分.
    @NonNull
    protected CancelType cancelType;

    public TerminateService() {
        cancelType = CANCEL;
    }

    @Override
    public ResultCode provision(ServiceProvisioning provisionEntity, ProvisioningLog logEntity) {
        ResultCode resultCode = UNKNOWN;

        UserStatus userStatus = new UserStatus();
        boolean canTerminate = canTerminate(provisionEntity, userStatus);

        if (!canTerminate) {
            super.history(provisionEntity, logEntity, INVALID_USER_STATUS);
            return INVALID_USER_STATUS;
        }
        TerminateRequest request = new TerminateRequest(provisionEntity.getCableId(), cancelType);
        resultCode = teminate(request, provisionEntity);

        if (resultCode == SUCCESS) {
            updateUserStatus(userStatus);
        }
        super.history(provisionEntity, logEntity, resultCode);
        return resultCode;
    }

    /**
     * 利用者ステータステーブルに照会し、即時解約可能かを判定する.<br>
     * 契約中 or 利用停止中なら即時解約可能.<br>
     * 加えて、Selectしたレコードを出力する.
     * 
     * @param serviceProvisioning サービスプロビジョニングテーブル
     * @param userStatus          利用者ステータステーブル
     * @return true: 即時解約可能
     */
    private boolean canTerminate(ServiceProvisioning serviceProvisioning, UserStatus userStatus) {

        UserStatusKey key = new UserStatusKey();
        BeanUtils.copyProperties(serviceProvisioning, key);

        UserStatus record = super.userStatusMapper.selectByPrimaryKey(key);
        if (record == null) {
            super.logger.log("SIM.ERROR.2000", serviceProvisioning.getSubscriberId());
            return false;
        }
        BeanUtils.copyProperties(record, userStatus);
        Status currentStatus = Status.valueFrom(userStatus.getUserStatusCode());

        if (NORMAL == currentStatus || SUSPENDED == currentStatus) {
            return true;
        } else {
            super.logger.log("SIM.ERROR.2001", currentStatus);
            return false;
        }
    }

    /**
     * 解約APIをコールし結果をEntityに設定する.
     * 
     * @param request 解約APIのリクエスト
     * @return 結果コード
     */
    private ResultCode teminate(TerminateRequest request, ServiceProvisioning entity) {

        ResponseEntity<TerminateResponse> res = super.postForEntity(terminateUrl, request, TerminateResponse.class);
        ResultCode resultCode = super.toResultCode(res);

        if (SUCCESS != resultCode) {
            return resultCode;
        }
        TerminateResponse result = res.getBody();
        CancelResultCode jsonCode = result.getResult_code();
        if (jsonCode == null) {
            return NG_RESPONSE;
        }
        switch (jsonCode) {
        case OK:
            return SUCCESS;
        case ALREADY_CANCELED:
            return NG_ALREADY_CANCELED;
        case ALREADY_PENDING:
            return NG_ALREADY_PENDING;
        case BILLING_HOLD:
            return NG_BILLING_HOLD;
        case INVALID_ACCOUNT_STATUS:
            return NG_INVALID_ACCOUNT_STATUS;
        case ALREADY_SUBSCRIBED_OTHER:
            return NG_ALREADY_SUBSCRIBED_OTHER;
        case INVALID_CABLE_ID:
            return NG_INVALID_CABLE_ID;
        case INTERNAL_ERROR:
            return NG_INTERNAL_ERROR;
        default:
            return NG_RESPONSE;
        }
    }

    /**
     * 利用者ステータステーブルのステータスを更新する.
     * 
     * @param userStatus 利用者ステータス
     */
    private void updateUserStatus(UserStatus userStatus) {

        userStatus.setUserStatusCode(TERMINATED.getCode());
        super.userStatusMapper.updateByPrimaryKeySelective(userStatus);
    }

}
