package foo.sample.batch;

import static foo.sample.constant.control.StepExitCode.MOVE_FAILED;
import static foo.sample.constant.control.StepExitCode.NOT_EXIST;

import java.lang.reflect.Field;
import java.util.Arrays;

import org.apache.commons.io.FilenameUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.ExecutionContextPromotionListener;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.FileSystemResource;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;

import foo.sample.app.config.RestTemplateBuilderSupplier;
import foo.sample.batch.job.listener.JobListener;
import foo.sample.batch.job.step.processor.ProvisioningCsvItemProcessor;
import foo.sample.batch.job.step.tasklet.ChargeReportTasklet;
import foo.sample.batch.job.step.tasklet.ChargeSummaryTasklet;
import foo.sample.batch.job.step.tasklet.ExistCheckTasklet;
import foo.sample.batch.job.step.tasklet.FileMoveTasklet;
import foo.sample.batch.job.step.writer.CsvFileItemWriter;
import foo.sample.batch.service.CablePlatformService;
import foo.sample.batch.service.ContractService;
import foo.sample.batch.service.ResumeService;
import foo.sample.batch.service.SuspendService;
import foo.sample.batch.service.TerminateCancelService;
import foo.sample.batch.service.TerminateReserveService;
import foo.sample.batch.service.TerminateService;
import foo.sample.constant.control.ContextKey;
import foo.sample.db.DataSourceConfiguration;
import foo.sample.file.entity.ProvisioningCsv;

@Configuration
@ComponentScan("foo.sample.component")
@ComponentScan("foo.sample.batch.service.CablePlatformService")
@ComponentScan("foo.sample.batch.service.ContractService")
@ComponentScan("foo.sample.batch.service.ResumeService")
@ComponentScan("foo.sample.batch.service.SuspendService")
@ComponentScan("foo.sample.batch.service.TerminateReserveService")
@ComponentScan("foo.sample.batch.service.TerminateService")
@ComponentScan("foo.sample.batch.service.TerminateCancelService")
@Import(DataSourceConfiguration.class)
@EnableBatchProcessing
public class SmsLinkConfiguration {

    @Value("${sms.contract.directory.upload}")
    private String uploadDirectory;
    @Value("${sms.contract.directory.download}")
    private String downloadDirectory;
    @Value("${sms.contract.workdirectory}")
    private String workDirectory;
    @Value("${sms.contract.filename}")
    private String fileNamePattern;

    @Value("${extension.result}")
    private String resultExtension;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;
    @Autowired
    private FlatFileItemReader<ProvisioningCsv> csvReader;
    @Autowired
    private ProvisioningCsvItemProcessor contractProcessor;
    @Autowired
    private CsvFileItemWriter<ProvisioningCsv> resultWriter;
    @Autowired
    private FileMoveTasklet fileMoveTasklet;

    @Bean
    public JobListener jobListener() {
        return new JobListener();
    }

    @Bean
    public ExecutionContextPromotionListener promotionListener() {
        ExecutionContextPromotionListener promotionListener = new ExecutionContextPromotionListener();
        // @formatter:off
        String[] keys = Arrays.stream(ContextKey.values())
                .map(ContextKey::getKey)
                .toArray(String[]::new);
        // @formatter:on
        promotionListener.setKeys(keys);
        return promotionListener;
    }

    /**
     * SMS連携CSVファイルを読込み、プロビジョニングを行う.
     * 
     * @return プロビジョニングJob
     */
    @Bean
    public Job provisioningJob() {
        // @formatter:off
        return jobBuilderFactory
                .get("provisioningJob")
                .incrementer(new RunIdIncrementer())
                .listener(jobListener())
                .start(existCheckStep()).on(NOT_EXIST.getCode()).end()
                .from(existCheckStep()).next(fileMoveStep()).on(MOVE_FAILED.getCode()).end()
                .from(fileMoveStep()).next(provisioningStep()).build()
                .build();
        // @formatter:on
    }

    @Bean
    public Step existCheckStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("existCheckStep")
                .listener(promotionListener())
                .tasklet(existCheckTasklet())
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    public ExistCheckTasklet existCheckTasklet() {
        return new ExistCheckTasklet(uploadDirectory, fileNamePattern);
    }

    @Bean
    public Step fileMoveStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("fileMoveStep")
                .listener(promotionListener())
                .tasklet(fileMoveTasklet)
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    @StepScope
    public FileMoveTasklet fileMoveTasklet(@Value("#{jobExecutionContext['sms_file_path']}") String filePath) {
        return new FileMoveTasklet(filePath, workDirectory);
    }

    @Bean
    public Step provisioningStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("provisioningStep")
                .<ProvisioningCsv, ProvisioningCsv>chunk(1)
                .reader(csvReader)
                .processor(contractProcessor)
                .writer(resultWriter)
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    @StepScope
    public FlatFileItemReader<ProvisioningCsv> csvReader(
            @Value("#{jobExecutionContext['sms_work_file_path']}") String filePath) {

        // @formatter:off
        return new FlatFileItemReaderBuilder<ProvisioningCsv>()
                .name("provisioningCsvItemReader")
                .resource(new FileSystemResource(filePath))
                .delimited()
                .quoteCharacter('\"')
                .names(new String[] { 
                        "subscriberId",
                        "operateType",
                        "catvCode",
                        "personalId",
                        "loginId",
                        "password",
                        "subscriberFlag",
                        "birthday",
                        "adultContentsRegulation",
                        "commonProductCode",
                        "gender",
                        "mailAddress",
                        "postalCode",
                        "suspendFlag",
                        "purchaseLimit",
                        "spId",
                        "dueDate" })
                .fieldSetMapper(new BeanWrapperFieldSetMapper<ProvisioningCsv>() {{
                    setTargetType(ProvisioningCsv.class);
                }})
                .build();
        // @formatter:on
    }

    @Bean
    @StepScope
    public ProvisioningCsvItemProcessor contractProcessor(
            @Value("#{jobExecutionContext['sms_file_path']}") String filePath) {
        return new ProvisioningCsvItemProcessor(filePath);
    }

    @Bean
    @StepScope
    public CsvFileItemWriter<ProvisioningCsv> resultWriter(
            @Value("#{jobExecutionContext['sms_file_path']}") String filePath) {

        String fileName = FilenameUtils.getName(filePath) + resultExtension;
        // @formatter:off
        String[] fieldNames = Arrays.stream(ProvisioningCsv.class.getDeclaredFields())
                .map(Field::getName)
                .toArray(String[]::new);
        // @formatter:on
        return new CsvFileItemWriter<ProvisioningCsv>(downloadDirectory, fileName, fieldNames);
    }

    @Bean
    public RestTemplateBuilderSupplier restTemplateBuilderSupplier() {
        return new RestTemplateBuilderSupplier();
    }

    @Bean
    public ObjectMapper objectMapper() {
        // @formatter:off
        return new ObjectMapper()
                .setSerializationInclusion(Include.NON_NULL);
        // @formatter:on
    }

    /**
     * 課金テーブルを読み、CSVファイル出力する.
     * 
     * @return 課金レポートJob
     */
    @Bean
    public Job chargeReportJob() {
        // @formatter:off
        return jobBuilderFactory
                .get("chargeReportJob")
                .incrementer(new RunIdIncrementer())
                .start(chargeReportStep())
                .build();
        // @formatter:on
    }

    @Bean
    public Step chargeReportStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("chargeReportStep")
                .tasklet(chargeReportTasklet())
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    public ChargeReportTasklet chargeReportTasklet() {
        return new ChargeReportTasklet();
    }

    @Bean
    @StepScope
    public CablePlatformService cablePlatformService() {
        CablePlatformService cablePlatformService = new CablePlatformService();
        return cablePlatformService;
    }

    @Bean
    @StepScope
    public ContractService contractService() {
        ContractService contractService = new ContractService();
        return contractService;
    }

    @Bean
    @StepScope
    public ResumeService resumeService() {
        ResumeService resumeService = new ResumeService();
        return resumeService;
    }

    @Bean
    @StepScope
    public SuspendService suspendService() {
        SuspendService suspendService = new SuspendService();
        return suspendService;
    }

    @Bean
    @StepScope
    public TerminateService terminateService() {
        TerminateService terminateService = new TerminateService();
        return terminateService;
    }

    @Bean
    @StepScope
    public TerminateReserveService terminateReserveService() {
        TerminateReserveService terminateReserveService = new TerminateReserveService();
        return terminateReserveService;
    }

    @Bean
    @StepScope
    public TerminateCancelService terminateCancelService() {
        TerminateCancelService terminateCancelService = new TerminateCancelService();
        return terminateCancelService;
    }

    /**
     * 事業者間精算レポート出力する.
     * 
     * @return 事業者間精算レポートJob
     */
    @Bean
    public Job chargeSummaryJob() {
        // @formatter:off
        return jobBuilderFactory
                .get("chargeSummaryJob")
                .incrementer(new RunIdIncrementer())
                .start(chargeSummaryStep())
                .build();
        // @formatter:on
    }

    @Bean
    public Step chargeSummaryStep() {
        // @formatter:off
        return stepBuilderFactory
                .get("chargeSummaryStep")
                .tasklet(chargeSummaryTasklet())
                .allowStartIfComplete(true)
                .build();
        // @formatter:on
    }

    @Bean
    public ChargeSummaryTasklet chargeSummaryTasklet() {
        return new ChargeSummaryTasklet();
    }

}
