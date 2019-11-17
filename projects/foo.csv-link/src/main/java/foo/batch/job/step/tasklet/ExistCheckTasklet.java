package foo.sample.batch.job.step.tasklet;

import static foo.sample.constant.control.ContextKey.SMS_CSV_FILEPATH;
import static foo.sample.constant.control.StepExitCode.NOT_EXIST;
import static org.springframework.batch.repeat.RepeatStatus.FINISHED;
import static foo.sample.constant.ResultCode.ERROR_SETTING;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

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
 * 正規表現のファイル名にて、指定Directoryに読込ファイルがあるかチェックする.
 */
public class ExistCheckTasklet implements Tasklet, InitializingBean {

    @Autowired
    private LogComponent logger;

    private String directoryPath;
    private Path path;

    private String fileNamePattern;
    private Pattern pattern;

    /**
     * パラメータをセットし、初期化.
     * 
     * @param directoryPath   連携ディレクトリ
     * @param fileNamePattern ファイル名の正規表現
     */
    public ExistCheckTasklet(String directoryPath, String fileNamePattern) {

        this.directoryPath = directoryPath;
        this.fileNamePattern = fileNamePattern;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (StringUtils.isAnyBlank(directoryPath, fileNamePattern)) {
            throw new ServiceException(ERROR_SETTING);
        }
        path = Paths.get(directoryPath);
        pattern = Pattern.compile(fileNamePattern);
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        logger.log("SIM.INFO.0004");

        List<Path> files = Files.list(path).collect(Collectors.toList());
        for (Path file : files) {

            String fileName = file.getFileName().toString();
            Matcher matcher = pattern.matcher(fileName);

            if (!matcher.find()) {
                continue;
            }
            logger.log("SIM.INFO.0007", matcher.group());

            ExecutionContext context = chunkContext.getStepContext().getStepExecution().getExecutionContext();
            context.put(SMS_CSV_FILEPATH.getKey(), directoryPath + matcher.group());

            return FINISHED;
        }
        logger.log("SIM.INFO.0006");
        logger.log("SIM.INFO.0005");

        contribution.setExitStatus(new ExitStatus(NOT_EXIST.getCode()));
        return FINISHED;
    }

}
