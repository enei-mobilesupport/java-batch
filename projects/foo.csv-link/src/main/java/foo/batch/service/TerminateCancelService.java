package foo.sample.batch.service;

import static foo.sample.constant.ResultCode.INVALID_USER_STATUS;
import static foo.sample.constant.ResultCode.NG_ALREADY_CANCELED;
import static foo.sample.constant.ResultCode.NG_ALREADY_SUBSCRIBED_OTHER;
import static foo.sample.constant.ResultCode.NG_INTERNAL_ERROR;
import static foo.sample.constant.ResultCode.NG_INVALID_ACCOUNT_STATUS;
import static foo.sample.constant.ResultCode.NG_INVALID_CABLE_ID;
import static foo.sample.constant.ResultCode.NG_RESPONSE;
import static foo.sample.constant.ResultCode.SUCCESS;
import static foo.sample.constant.ResultCode.UNKNOWN;
import static foo.sample.constant.Status.NORMAL;
import static foo.sample.constant.Status.TERMINATED;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import foo.sample.app.json.dev.TerminateCancelRequest;
import foo.sample.app.json.dev.TerminateCancelResponse;
import foo.sample.batch.service.base.BaseProvisioningService;
import foo.sample.constant.devEnums.CancelResultCode;
import foo.sample.constant.ResultCode;
import foo.sample.constant.Status;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusKey;

/**
 * 解約予約取消のプロビジョニング.
 */
@Service
public class TerminateCancelService extends BaseProvisioningService {

    @Value("${api.dev.endpoint.account.cancel}")
    private String cancelUrl;

    @Override
    public ResultCode provision(ServiceProvisioning provisionEntity, ProvisioningLog logEntity) {
        ResultCode resultCode = UNKNOWN;

        UserStatus userStatus = new UserStatus();
        boolean canTerminateCancel = canTerminateCancel(provisionEntity, userStatus);

        if (!canTerminateCancel) {
            super.history(provisionEntity, logEntity, INVALID_USER_STATUS);
            return INVALID_USER_STATUS;
        }
        TerminateCancelRequest request = new TerminateCancelRequest(provisionEntity.getCableId());
        resultCode = teminateCancel(request, provisionEntity);

        if (resultCode == SUCCESS) {
            updateUserStatus(userStatus);
        }
        super.history(provisionEntity, logEntity, resultCode);
        return resultCode;
    }

    /**
     * 利用者ステータステーブルを照会し、解約予約取消が可能かを判定する.<br>
     * 解約済みなら解約予約取消が可能.<br>
     * 加えて、Selectしたレコードを出力する.
     * 
     * @param serviceProvisioning サービスプロビジョニングテーブル
     * @param userStatus          利用者ステータステーブル
     * @return true: 解約予約取消 可能
     */
    private boolean canTerminateCancel(ServiceProvisioning serviceProvisioning, UserStatus userStatus) {

        UserStatusKey key = new UserStatusKey();
        BeanUtils.copyProperties(serviceProvisioning, key);

        UserStatus record = super.userStatusMapper.selectByPrimaryKey(key);
        if (record == null) {
            super.logger.log("SIM.ERROR.2000", serviceProvisioning.getSubscriberId());
            return false;
        }
        BeanUtils.copyProperties(record, userStatus);
        Status currentStatus = Status.valueFrom(userStatus.getUserStatusCode());

        if (TERMINATED == currentStatus) {
            return true;
        } else {
            super.logger.log("SIM.ERROR.2001", currentStatus);
            return false;
        }
    }

    /**
     * 解約予約取消APIをコールし結果をEntityに設定する.
     * 
     * @param request 解約予約取消APIのリクエスト
     * @return 結果コード
     */
    private ResultCode teminateCancel(TerminateCancelRequest request, ServiceProvisioning entity) {

        ResponseEntity<TerminateCancelResponse> res = super.postForEntity(cancelUrl, request,
                TerminateCancelResponse.class);
        ResultCode resultCode = super.toResultCode(res);

        if (SUCCESS != resultCode) {
            return resultCode;
        }
        TerminateCancelResponse result = res.getBody();
        CancelResultCode jsonCode = result.getResult_code();
        if (jsonCode == null) {
            return NG_RESPONSE;
        }
        switch (jsonCode) {
        case OK:
            return SUCCESS;
        case ALREADY_CANCELED:
            return NG_ALREADY_CANCELED;
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

        userStatus.setUserStatusCode(NORMAL.getCode());
        super.userStatusMapper.updateByPrimaryKeySelective(userStatus);
    }

}
