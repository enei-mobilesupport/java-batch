package foo.sample.batch.job.step.tasklet;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import foo.sample.component.LogComponent;
import foo.sample.constant.log.OperationId;

public class ResultOkFileTasklet implements Tasklet, InitializingBean {

    @Autowired
    private LogComponent logger;

    @Value("${sp.charge.filename}")
    private String filePattern;
    
    @Value("${extension.csv}")
    private String suffix_csv;
    
    @Value("${extension.ok}")
    private String suffix_ok;

    @Value("${extension.result}")
    private String suffix_result;
    
    @Value("${sp.charge.directory}")
    private String fileDirPath;

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.setTarget("", "", OperationId.DAILY.toString(), "");
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        
        Path path = Paths.get(fileDirPath);
        String inputDirPath = path.toUri().toString() + "/*";

        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resultResources = resourcePatternResolver.getResources(inputDirPath);
        Resource[] resultOkResources = resourcePatternResolver.getResources(inputDirPath);

        String resultFilePattern = filePattern + suffix_csv + suffix_result + "$";
        logger.debug("result File pattern : " + resultFilePattern);
        Pattern resultPattern = Pattern.compile(resultFilePattern);

        boolean isDone = false;
        List<String> resultFileList = new ArrayList<>();
        
        for (Resource resultResource : resultResources) {
            String resultFileName = resultResource.getFilename();
            String checkFileName = FilenameUtils.getBaseName(resultFileName) + suffix_result + suffix_ok;
            Matcher matcher = resultPattern.matcher(resultFileName);
            if (matcher.find()) {
                logger.debug("result File name matched.[filename=" + resultFileName + "]");

                for (Resource resultOkResource : resultOkResources) {
                    isDone = false;

                    String resultOkFileName = resultOkResource.getFilename();
                    if (resultOkFileName.equals(checkFileName)) {
                        isDone = true;
                        break;
                    }
                }
                if (!isDone) {
                    resultFileList.add(resultFileName);
                } else {
                    logger.debug(".result.ok file already exist.");
                }
            }
        }


        // okファイルが存在しないresultファイルのリソースを集約
        for (String fileName : resultFileList) {
            // result.okファイル作成
            Resource resource = new FileSystemResource(fileDirPath + fileName + suffix_ok);
            File okfile = new File(resource.getURI());
            okfile.createNewFile();
            logger.log("SIM.INFO.0016", okfile);
        }
        return FINISHED;
    }

}
