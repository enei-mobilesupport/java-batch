package foo.sample.batch.job.listener;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.component.LogComponent;
import foo.sample.constant.log.OperationId;

/**
 * Jobの開始時と終了時のメッセージをログへ出力する.
 */
public class JobListener implements JobExecutionListener {

    @Autowired
    private LogComponent logger;
    
    private static final String SERVICE_PROV = "provisioningJob";
    private static final String DAILY_CHARGE = "dailyChargeJob";

    @Override
    public void beforeJob(JobExecution jobExecution) {

        //TODO job名でメッセージを出し分けるのではなく、job名をそのまま出してしまうか検討する
        String jobName = jobExecution.getJobInstance().getJobName();
        if (jobName.equals(SERVICE_PROV)) {
            logger.setTarget("", "", "PROVISION", "");
            logger.log("SIM.INFO.0001");
        } else if (jobName.equals(DAILY_CHARGE)) {
            logger.setTarget("", "", OperationId.DAILY.toString(), "");
            logger.log("SIM.INFO.0026");
        } else {
            logger.debug("job name =[" + jobName + "]");
        }
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String jobName = jobExecution.getJobInstance().getJobName();
        //TODO job名でメッセージを出し分けるのではなく、job名をそのまま出してしまうか検討する
        if (jobName.equals(SERVICE_PROV)) {
            logger.log("SIM.INFO.0002");
        } else if (jobName.equals(DAILY_CHARGE)) {
            logger.log("SIM.INFO.0027");
        } else {
            logger.debug("job name =[" + jobName + "]");
        }
    }

}
