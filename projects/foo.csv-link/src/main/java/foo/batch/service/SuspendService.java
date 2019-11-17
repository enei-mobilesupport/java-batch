package foo.sample.batch.service;

import static foo.sample.constant.ResultCode.INVALID_USER;
import static foo.sample.constant.ResultCode.INVALID_USER_STATUS;
import static foo.sample.constant.ResultCode.SUCCESS;
import static foo.sample.constant.ResultCode.UNKNOWN;
import static foo.sample.constant.Status.NORMAL;
import static foo.sample.constant.Status.SUSPENDED;

import org.springframework.beans.BeanUtils;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import foo.sample.batch.service.base.BaseProvisioningService;
import foo.sample.constant.ResultCode;
import foo.sample.constant.Status;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusKey;

/**
 * 利用停止のプロビジョニング.<br>
 * 利用者ステータステーブルに照会し、契約中なら、利用停止にする.
 */
@Service
public class SuspendService extends BaseProvisioningService {

    // 更新前のステータス
    @NonNull
    protected Status currentStatus;
    // 更新後のステータス
    @NonNull
    protected Status updatedStatus;

    public SuspendService() {
        currentStatus = NORMAL;
        updatedStatus = SUSPENDED;
    }

    @Override
    public ResultCode provision(ServiceProvisioning provisionEntity, ProvisioningLog logEntity) {
        ResultCode resultCode = UNKNOWN;

        UserStatusKey key = new UserStatusKey();
        BeanUtils.copyProperties(provisionEntity, key);

        UserStatus userStatus = super.userStatusMapper.selectByPrimaryKey(key);

        if (userStatus == null) {
            resultCode = INVALID_USER;

        } else if (currentStatus == Status.valueFrom(userStatus.getUserStatusCode())) {

            userStatus.setUserStatusCode(updatedStatus.getCode());
            super.userStatusMapper.updateByPrimaryKeySelective(userStatus);
            resultCode = SUCCESS;

        } else {
            resultCode = INVALID_USER_STATUS;
        }
        super.history(provisionEntity, logEntity, resultCode);
        return resultCode;
    }

}
