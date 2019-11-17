package foo.sample.batch.job.step.writer;

import static foo.sample.constant.ResultCode.ERROR_FILE_WRITE;
import static foo.sample.constant.ResultCode.ERROR_SETTING;

import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import foo.sample.batch.exception.ServiceException;
import foo.sample.component.LogComponent;
import lombok.Setter;

/**
 * 指定Directoryに、CSV形式のファイル出力.
 *
 * @param <T> 出力カラム
 */
public class CsvFileItemWriter<T> implements ItemWriter<T>, InitializingBean {

    @Autowired
    private LogComponent logger;

    private String directoryPath;

    private String fileName;

    private String[] fieldNames;

    @Setter
    private ExecutionContext executionContext = new ExecutionContext();

    private Resource resource;

    /**
     * パラメータをセットし、初期化.
     * 
     * @param directoryPath 連携ディレクトリ
     * @param fileName      ファイル名(例:CC99999999_SIM_contract_20190401025855.csv.result)
     * @param fieldNames    出力項目
     */
    public CsvFileItemWriter(String directoryPath, String fileName, String[] fieldNames) {

        this.directoryPath = directoryPath;
        this.fileName = fileName;
        this.fieldNames = fieldNames;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if (logger == null) {
            throw new ServiceException(ERROR_SETTING);
        }
        if (StringUtils.isAnyBlank(directoryPath, fileName)) {
            logger.log("SIM.FATAL.1000");
            throw new ServiceException(ERROR_FILE_WRITE);
        }
        if (!StringUtils.endsWith(directoryPath, "/")) {
            logger.log("SIM.FATAL.0001", "連携ディレクトリパス");
            throw new ServiceException(ERROR_SETTING);
        }
        if (ArrayUtils.isEmpty(fieldNames)) {
            logger.log("SIM.ERROR.9992", "【想定外】ソースコードに問題があります。");
            throw new ServiceException(ERROR_FILE_WRITE);
        }
        resource = new FileSystemResource(directoryPath + fileName);
    }

    @Override
    public void write(List<? extends T> items) throws Exception {
        FlatFileItemWriter<T> writer = new FlatFileItemWriter<>();

        writer.setAppendAllowed(true);
        writer.setResource(resource);
        writer.setLineSeparator("\r\n");

        writer.setLineAggregator(new EnclosableDelimitedLineAggregator<T>() {
            {
                setFieldExtractor(new BeanWrapperFieldExtractor<T>() {
                    {
                        setNames(fieldNames);
                    }
                });
            }
        });
        writer.afterPropertiesSet();

        writer.open(executionContext);
        writer.write(items);

        logger.log("SIM.INFO.0015", resource);

    }
}
