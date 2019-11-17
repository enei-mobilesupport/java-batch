package foo.sample.batch.service;

import static foo.sample.constant.devEnums.CancelType.PENDING;

import org.springframework.stereotype.Service;

/**
 * 解約予約のプロビジョニング.
 */
@Service
public class TerminateReserveService extends TerminateService {

    public TerminateReserveService() {
        super.cancelType = PENDING;
    }

}
