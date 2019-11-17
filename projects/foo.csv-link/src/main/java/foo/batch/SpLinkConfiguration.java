package foo.sample.batch;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.partition.support.MultiResourcePartitioner;
import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.Resource;

import foo.sample.batch.job.listener.JobListener;
import foo.sample.batch.job.step.listener.StepListener;
import foo.sample.batch.job.step.partitioner.DailyChargeFilePartitioner;
import foo.sample.batch.job.step.processor.ChargeCsvItemProcessor;
import foo.sample.batch.job.step.tasklet.ResultOkFileTasklet;
import foo.sample.batch.job.step.writer.CsvFileItemWriter;
import foo.sample.batch.service.DailyChargeService;
import foo.sample.component.LogComponent;
import foo.sample.constant.log.OperationId;
import foo.sample.db.DataSourceConfiguration;
import foo.sample.db.auto.ref.RefDbDataSourceConfiguration;
import foo.sample.file.entity.ChargeCsv;

@Configuration
@ComponentScan("foo.sample.batch.service.DailyChargeService")
@ComponentScan("foo.sample.component")
@Import({ DataSourceConfiguration.class, RefDbDataSourceConfiguration.class })
@EnableBatchProcessing
public class SpLinkConfiguration {

    @Autowired
    private LogComponent logger;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private ChargeCsvItemProcessor dailyChargeProcessor;

    @Autowired
    public ItemReader<ChargeCsv> chargeReader;

    @Autowired
    public CsvFileItemWriter<ChargeCsv> chargeWriter;

    @Autowired
    private DailyChargeFilePartitioner dailyChargeFilePartitioner;

    @Autowired
    ApplicationContext context;

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

    private static final String[] CHARGE_CSV_FIELDS = Arrays.stream(ChargeCsv.class.getDeclaredFields())
            .map(Field::getName).toArray(String[]::new);

    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }

    @Bean
    public StepListener stepListener() {
        return new StepListener();
    }

    /**
     * サービスプロバイダから連携された請求ファイルを読込み、請求可否判定を行う.
     * 
     * @return dailyChargeJob
     */
    @Bean
    public Job dailyChargeJob() {
        // @formatter:off
        logger.setTarget("", "", OperationId.DAILY.toString(), "");

        return jobBuilderFactory
                .get("dailyChargeJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener())
                .start(dailyFileStep())
                .next(okResultFileStep())
                .build();
        // @formatter:on

    }

    @Bean
    public Step dailyFileStep() {
        Partitioner partitioner = context.getBean(MultiResourcePartitioner.class);

        // @formatter:off
        return stepBuilderFactory
                .get("dailyFileStep")
                .partitioner(SlaveDailyFileStep())
                .partitioner("SlaveDailyFileStep", partitioner)
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    public Step SlaveDailyFileStep() {

        // @formatter:off
        return stepBuilderFactory
                .get("dailyFileStep")
                .listener(stepListener())
                .<ChargeCsv, ChargeCsv>chunk(1)
                .reader(chargeReader)
                .processor(dailyChargeProcessor)
                .writer(chargeWriter)
                .allowStartIfComplete(true)
                .build();
        // @formatter:on

    }

    @Bean
    public Step okResultFileStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("resultOkFileTasklet")
                .listener(stepListener())
                .tasklet(resultOkFileTasklet())
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    @StepScope
    public ResultOkFileTasklet resultOkFileTasklet() {
        return new ResultOkFileTasklet();
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ChargeCsv> chargeReader(@Value("#{stepExecutionContext[fileName]}") Resource file) {
        logger.debug("Reader : " + file.getFilename());
        logger.debug("File Pattern : " + filePattern);
        // @formatter:off
        FlatFileItemReader<ChargeCsv> reader = new FlatFileItemReaderBuilder<ChargeCsv>()
                .name("SpCsvItemReader")
                .encoding(StandardCharsets.UTF_8.name())
                .resource(file) 
                .delimited()
                .quoteCharacter('\"')
                .names(CHARGE_CSV_FIELDS)
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ChargeCsv>() {{
                    setTargetType(ChargeCsv.class);
                }})
                .build();
        // @formatter:on

        return reader;
    }

    @Bean
    @StepScope
    public ChargeCsvItemProcessor dailyChargeProcessor(@Value("#{stepExecutionContext[fileName]}") Resource file) {
        ChargeCsvItemProcessor processor = new ChargeCsvItemProcessor();
        return processor;
    }

    @Bean
    @StepScope
    public CsvFileItemWriter<ChargeCsv> chargeWriter(@Value("#{stepExecutionContext[fileName]}") Resource file)
            throws IOException {

        // .resultファイル書き込み
        String resultFileName = file.getFilename() + suffix_result;
        Path path = Paths.get(fileDirPath, resultFileName);

        String destFile = path.toUri().toString();
        logger.debug("Ouput file : " + destFile);

        CsvFileItemWriter<ChargeCsv> writer = new CsvFileItemWriter<ChargeCsv>(fileDirPath, resultFileName,
                CHARGE_CSV_FIELDS);

        return writer;
    }

    @Bean
    @StepScope
    public MultiResourcePartitioner paritioner() throws IOException {
        return dailyChargeFilePartitioner.partitioner();
    }

    @Bean
    @StepScope
    public DailyChargeService dailyChargeService(@Value("#{stepExecutionContext[fileName]}") Resource file) {
        DailyChargeService dailyChargeService = new DailyChargeService(file);
        return dailyChargeService;
    }

    @Bean
    @StepScope
    public DailyChargeFilePartitioner dailyChargeFilePartitioner() {
        DailyChargeFilePartitioner dailyChargeFilePartitioner = new DailyChargeFilePartitioner();
        return dailyChargeFilePartitioner;
    }

}
