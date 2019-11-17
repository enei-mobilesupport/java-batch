package foo.sample.batch.job.step.listener;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.component.LogComponent;

/**
 * Stepの開始時と終了時のメッセージをログへ出力する.
 */
public class StepListener implements StepExecutionListener {

    @Autowired
    private LogComponent logger;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        logger.setTarget("", "", "", "");
        String step = stepExecution.getStepName();
        logger.log("SIM.INFO.0032", step);
        ;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        logger.setTarget("", "", "", "");
        String step = stepExecution.getStepName();
        logger.log("SIM.INFO.0033", step);
        ;

        return null;
    }

}
