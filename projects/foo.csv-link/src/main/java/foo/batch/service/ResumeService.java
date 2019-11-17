package foo.sample.batch.service;

import static foo.sample.constant.Status.NORMAL;
import static foo.sample.constant.Status.SUSPENDED;

import org.springframework.stereotype.Service;

/**
 * 利用再開のプロビジョニング.<br>
 * 利用者ステータステーブルに照会し、利用停止なら、契約中にする.
 */
@Service
public class ResumeService extends SuspendService {

    public ResumeService() {
        super.currentStatus = SUSPENDED;
        super.updatedStatus = NORMAL;
    }

}
