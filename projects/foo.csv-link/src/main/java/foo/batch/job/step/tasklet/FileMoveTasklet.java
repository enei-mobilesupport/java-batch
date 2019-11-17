package foo.sample.batch.job.step.tasklet;

import static foo.sample.constant.ResultCode.ERROR_FILE_READ;
import static foo.sample.constant.ResultCode.ERROR_SETTING;
import static foo.sample.constant.control.ContextKey.SMS_WORK_CSV_FILEPATH;
import static foo.sample.constant.control.StepExitCode.MOVE_FAILED;
import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.batch.exception.ServiceException;
import foo.sample.component.LogComponent;

/**
 * 指定ディレクトリに、ファイルを移動する.
 */
public class FileMoveTasklet implements Tasklet, InitializingBean {

    @Autowired
    private LogComponent logger;

    private String filePath;
    private File srcFile;

    private String destDirPath;
    private File destDir;

    private String destFilePath;

    /**
     * パラメータをセットし、初期化.
     * 
     * @param filePath    移動元のファイルパス
     * @param destDirPath 移動先のディレクトリパス
     */
    public FileMoveTasklet(String filePath, String destDirPath) {

        this.filePath = filePath;
        this.destDirPath = destDirPath;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (logger == null) {
            throw new ServiceException(ERROR_SETTING);
        }
        if (StringUtils.isAnyBlank(filePath, destDirPath)) {
            logger.log("SIM.FATAL.1000");
            throw new ServiceException(ERROR_FILE_READ);
        }
        if (!StringUtils.endsWith(destDirPath, "/")) {
            logger.log("SIM.FATAL.0001", "ファイル移動先のディレクトリパス");
            throw new ServiceException(ERROR_SETTING);
        }
        destFilePath = destDirPath + FilenameUtils.getName(filePath);

        srcFile = new File(filePath);
        destDir = new File(destDirPath);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        ExecutionContext context = chunkContext.getStepContext().getStepExecution().getExecutionContext();
        try {
            FileUtils.moveFileToDirectory(srcFile, destDir, true);
            context.put(SMS_WORK_CSV_FILEPATH.getKey(), destFilePath);

            logger.log("SIM.INFO.0008", destFilePath);
            return FINISHED;

        } catch (IOException e) {
            contribution.setExitStatus(new ExitStatus(MOVE_FAILED.getCode()));

            logger.log("SIM.ERROR.2002", e, destFilePath);
            return FINISHED;
        }
    }

}
