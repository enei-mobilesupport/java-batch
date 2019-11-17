package foo.sample.batch.job.step.processor;

import static foo.sample.constant.ResultCode.ERROR_SETTING;
import static foo.sample.constant.ResultCode.INVALID_PROVISIONING_TYPE;
import static foo.sample.constant.ResultCode.UNKNOWN;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import foo.sample.app.json.cable.CRPD2020Response;
import foo.sample.app.json.cable.ServiceProviderInfo;
import foo.sample.batch.exception.ServiceException;
import foo.sample.batch.service.CablePlatformService;
import foo.sample.batch.service.ContractService;
import foo.sample.batch.service.ResumeService;
import foo.sample.batch.service.SuspendService;
import foo.sample.batch.service.TerminateCancelService;
import foo.sample.batch.service.TerminateReserveService;
import foo.sample.batch.service.TerminateService;
import foo.sample.component.LogComponent;
import foo.sample.constant.CablePlatformEnums.AuthorizeFlag;
import foo.sample.constant.CablePlatformEnums.ConsentFlag;
import foo.sample.constant.CablePlatformEnums.EnableFlag;
import foo.sample.constant.CablePlatformEnums.Gender;
import foo.sample.constant.CablePlatformEnums.SmsContractFlag;
import foo.sample.constant.Provisioning;
import foo.sample.constant.ResultCode;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.file.entity.ProvisioningCsv;

/**
 * サービスプロビジョニング処理.
 */
public class ProvisioningCsvItemProcessor implements ItemProcessor<ProvisioningCsv, ProvisioningCsv>, InitializingBean {
    @Autowired
    private LogComponent logger;

    @Autowired
    private CablePlatformService cablePlatformService;
    @Autowired
    private ContractService contractService;
    @Autowired
    private SuspendService suspendService;
    @Autowired
    private ResumeService resumeService;
    @Autowired
    private TerminateService terminateService;
    @Autowired
    private TerminateReserveService terminateReserveService;
    @Autowired
    private TerminateCancelService terminateCancelService;

    private String fileName;

    /**
     * 初期化して、インスタンス生成.
     * 
     * @param filePath サービスプロビジョニング要求の CSVファイルパス
     */
    public ProvisioningCsvItemProcessor(String filePath) {
        this.fileName = FilenameUtils.getName(filePath);
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        if (!ObjectUtils.allNotNull(logger, cablePlatformService, contractService, suspendService, resumeService,
                terminateService, terminateReserveService, terminateCancelService)) {

            throw new ServiceException(ERROR_SETTING);
        }
        if (!StringUtils.hasText(fileName)) {
            throw new ServiceException(ERROR_SETTING);
        }
    }

    @Override
    public ProvisioningCsv process(final ProvisioningCsv csv) throws Exception {
        try {
            return perform(csv);

        } catch (ServiceException e) {
            logger.log("SIM.ERROR.9991", e);
            csv.setResultCode(e.getErrorCode().getResultCode());
            return csv;

        } catch (Exception e) {
            logger.log("SIM.ERROR.9991", e);
            csv.setResultCode(UNKNOWN.getResultCode());
            return csv;
        }
    }

    /**
     * サービスプロビジョニング処理を行う.
     * 
     * @param csv サービスプロビジョニングCSVファイルの１行
     * @return 結果コードを含む CSVファイルの１行
     * @throws Exception エラー
     */
    private ProvisioningCsv perform(final ProvisioningCsv csv) throws Exception {

        Provisioning operation = Provisioning.valueOf(csv.getOperateType(), csv.getSuspendFlag(), csv.getDueDate());
        logger.setTarget(csv.getCatvCode(), csv.getSpId(), operation.name(), csv.getSubscriberId());

        CRPD2020Response response = cablePlatformService.fetchCableId(csv);
        String cableId = response.getCable_id();

        ServiceProvisioning provisionEntity = toServiceProvisioning(csv, fileName, cableId,
                response.getService_provider_info());
        ProvisioningLog logEntity = toProvisioningLog(csv, cableId, operation.getNote());

        ResultCode resultCode = UNKNOWN;
        switch (operation) {
        case CONTRACT:
            resultCode = contractService.provisioning(provisionEntity, logEntity);
            break;
        case SUSPEND:
            resultCode = suspendService.provisioning(provisionEntity, logEntity);
            break;
        case RESUME:
            resultCode = resumeService.provisioning(provisionEntity, logEntity);
            break;
        case TERMINATE:
            resultCode = terminateService.provisioning(provisionEntity, logEntity);
            break;
        case TERMINATE_RESERVE:
            resultCode = terminateReserveService.provisioning(provisionEntity, logEntity);
            break;
        case TERMINATE_RESERVE_CANCEL:
            resultCode = terminateCancelService.provisioning(provisionEntity, logEntity);
            break;
        default:
            resultCode = INVALID_PROVISIONING_TYPE;
        }
        csv.setResultCode(resultCode.getResultCode());
        return csv;
    }

    /**
     * csvファイルの１行をサービスプロビジョニングテーブルのレコードにマッピング.
     * 
     * @param csv      サービスプロビジョニング要求
     * @param fileName 連携ファイル名
     * @param cableId  ケーブルID
     * @param json     個人情報
     * @return DBのEntity
     * @throws ParseException 生年月日のパースエラー
     */
    private ServiceProvisioning toServiceProvisioning(final ProvisioningCsv csv, String fileName, String cableId,
            final ServiceProviderInfo json) throws ParseException {

        /** 郵便番号カラムのサイズ */
        final int POSTAL_CD_SIZE = 7;

        ServiceProvisioning entity = new ServiceProvisioning();
        BeanUtils.copyProperties(csv, entity);

        entity.setSpLinkFileName(fileName);
        entity.setCableId(cableId);

        if (json == null) {
            logger.log("SIM.WARN.9992", "【想定外】SP情報がレスポンスにありません。");
            return entity;
        }
        entity.setFullName(json.getFull_name_kanji()); // 氏名
        entity.setFullKana(json.getFull_name_kana()); // 氏名カナ

        String birthday = json.getBirthday();
        if (StringUtils.hasText(birthday)) {

            Date date = DateUtils.parseDate(birthday, Locale.JAPAN, "yyyy/MM/dd");
            entity.setBirthday(date); // 生年月日
        }
        Optional<Gender> gender = Optional.ofNullable(json.getGender());
        gender.ifPresent(g -> entity.setGender(g.getCode())); // 性別

        String postCode = json.getPostal_cd();
        if (StringUtils.hasLength(postCode) && postCode.length() > POSTAL_CD_SIZE) {
            logger.log("SIM.WARN.9992", "【想定外】郵便番号が７桁を越えています。");
            postCode = postCode.substring(0, POSTAL_CD_SIZE);
        }
        entity.setPostCode(postCode); // 郵便番号
        entity.setSmsContractId(json.getContract_id()); // 世帯ID

        Optional<SmsContractFlag> contractStatus = Optional.ofNullable(json.getContract_status());
        contractStatus.ifPresent(c -> entity.setSmsContractFlag(c.getCode())); // SMS契約識別情報

        Optional<AuthorizeFlag> authzStatus = Optional.ofNullable(json.getAuthz_status());
        authzStatus.ifPresent(a -> entity.setAuthorizeFlag(a.getCode())); // 認可ステータス

        Optional<EnableFlag> enable = Optional.ofNullable(json.getEnable());
        enable.ifPresent(e -> entity.setEnableFlag(e.getCode())); // 利用可否

        Optional<ConsentFlag> consent = Optional.ofNullable(json.getConsent());
        consent.ifPresent(c -> entity.setConsentFlag(c.getCode())); // 使用許諾有無

        return entity;
    }

    /**
     * csvファイルの１行をプロビログテーブルのレコードにマッピング.
     * 
     * @param csv             サービスプロビジョニング要求
     * @param operateTarget   操作対象
     * @param operateContents 操作内容
     * @return DBのEntity
     */
    private ProvisioningLog toProvisioningLog(final ProvisioningCsv csv, String operateTarget, String operateContents) {
        ProvisioningLog entity = new ProvisioningLog();

        BeanUtils.copyProperties(csv, entity);

        entity.setOperateTarget(operateTarget);
        entity.setOperateContents(operateContents);

        return entity;
    }

}
