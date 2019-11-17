package foo.sample.batch.job.step.partitioner;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import foo.sample.component.LogComponent;

public class DailyChargeFilePartitioner {

    @Autowired
    private LogComponent logger;

    @Value("${sp.charge.filename}")
    private String filePattern;

    @Value("${extension.ok}")
    private String suffix_ok;

    @Value("${extension.csv}")
    private String suffix_csv;

    @Value("${extension.result}")
    private String suffix_result;

    @Value("${sp.charge.directory}")
    private String fileDirPath;

    @Bean
    @StepScope
    public MultiResourcePartitioner partitioner() throws IOException {

        Path path = Paths.get(fileDirPath);
        String inputDirPath = path.toUri().toString() + "/*";

        // ディレクトリ内のファイルを集約
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources(inputDirPath);
        Map<String, Resource> resMap = new HashMap<String, Resource>();
        for (Resource res : resources) {
            resMap.put(res.getFilename(), res);
        }

        // 処理対象ファイル名
        String targetFileName = filePattern + suffix_csv + "$";
        Pattern targetFilePattern = Pattern.compile(targetFileName);
        // 処理対象ファイルを集約
        List<Resource> resourceList = new ArrayList<>();

        logger.log("SIM.INFO.0009");
        // パターンに一致するOKファイルを取得し、読込OKなファイルの名称を取得する。
        // 処理済み（.result に一致する）ファイルは除外する
        for (Resource resource : resources) {
            String fileName = resource.getFilename();
            Matcher matcher = targetFilePattern.matcher(fileName);
            if (matcher.find()) {
                String okFileName = fileName + suffix_ok;
                String resultFileName = fileName + suffix_result;

                // okファイルあり、かつ resultファイルがない
                if (resMap.containsKey(okFileName) && !resMap.containsKey(resultFileName)) {
                    logger.debug("target File name matched= [" + fileName + "]");
                    resourceList.add(resource);
                }
            }
        }

        logger.log("SIM.INFO.0025", resourceList);
        Resource[] okResources = resourceList.toArray(new Resource[resourceList.size()]);
        MultiResourcePartitioner partitioner = new MultiResourcePartitioner();
        partitioner.partition(1);
        partitioner.setResources(okResources);

        logger.log("SIM.INFO.0010");

        return partitioner;
    }

}
