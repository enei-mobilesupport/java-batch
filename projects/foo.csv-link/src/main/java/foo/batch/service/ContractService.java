package foo.sample.batch.service;

import static foo.sample.constant.devEnums.EntryType.ENTRY;
import static foo.sample.constant.devEnums.Gender.NOT_KNOWN;
import static foo.sample.constant.ResultCode.INVALID_USER_STATUS;
import static foo.sample.constant.ResultCode.NG_ALREADY_SUBSCRIBED_CABLE_ID;
import static foo.sample.constant.ResultCode.NG_ALREADY_SUBSCRIBED_OTHER;
import static foo.sample.constant.ResultCode.NG_ALREADY_USED_CABLE_ID;
import static foo.sample.constant.ResultCode.NG_ALREADY_USED_EMAIL;
import static foo.sample.constant.ResultCode.NG_EMAIL_AND_CABLE_ID_MISMATCHED;
import static foo.sample.constant.ResultCode.NG_EMAIL_AND_SERVICE_CODE_MISMATCHED;
import static foo.sample.constant.ResultCode.NG_INTERNAL_ERROR;
import static foo.sample.constant.ResultCode.NG_INVALID_CAMPAIGN_ID;
import static foo.sample.constant.ResultCode.NG_RESPONSE;
import static foo.sample.constant.ResultCode.SUCCESS;
import static foo.sample.constant.Status.TERMINATED;
import static org.apache.commons.lang3.time.DateFormatUtils.ISO_8601_EXTENDED_DATE_FORMAT;

import java.util.Date;
import java.util.Optional;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import foo.sample.app.json.dev.ContractRequest;
import foo.sample.app.json.dev.ContractResponse;
import foo.sample.app.json.dev.VerifyRequest;
import foo.sample.app.json.dev.VerifyResponse;
import foo.sample.batch.service.base.BaseProvisioningService;
import foo.sample.constant.CablePlatformEnums.Gender;
import foo.sample.constant.devEnums.EntryType;
import foo.sample.constant.devEnums.VerifyResultCode;
import foo.sample.constant.ResultCode;
import foo.sample.constant.Status;
import foo.sample.constant.base.BaseCodeIf;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusKey;

/**
 * 契約申込のプロビジョニング.
 */
@Service
public class ContractService extends BaseProvisioningService {
    /** 全角スペース */
    private static final String EM_SPACE;
    static {
        char[] emSpace = { '\u3000' };
        EM_SPACE = new String(emSpace);
    }
    /** JDSデフォルト値_姓 */
    private static final String LAST_NAME = "<blank>";
    /** JDSデフォルト値_名 */
    private static final String FIRST_NAME = "<blank>";
    /** JDSデフォルト値_性別 */
    private static final foo.sample.constant.devEnums.Gender GENDER = NOT_KNOWN;
    /** JDSデフォルト値_生年月日 */
    private static final String BIRTH_DATE = "1900-01-01";

    @Value("${api.dev.endpoint.account.get}")
    private String verifyUrl;
    @Value("${api.dev.endpoint.account.put}")
    private String contractUrl;

    @Override
    public ResultCode provision(ServiceProvisioning provisionEntity, ProvisioningLog logEntity) {

        boolean canContract = canContract(provisionEntity);
        if (!canContract) {
            super.history(provisionEntity, logEntity, INVALID_USER_STATUS);
            return INVALID_USER_STATUS;
        }
        ContractRequest request = toContractRequest(provisionEntity);

        ResultCode resultCode = verify(request);
        if (SUCCESS != resultCode) {

            super.history(provisionEntity, logEntity, resultCode);
            return resultCode;
        }
        resultCode = contract(request, provisionEntity);

        super.history(provisionEntity, logEntity, resultCode);
        return resultCode;
    }

    /**
     * 利用者ステータステーブルに照会し、レコード無し or 解約済みなら、入会可能.
     * 
     * @param entity サービスプロビジョニングテーブル
     * @return true: 新規入会／再入会可
     */
    private boolean canContract(ServiceProvisioning entity) {

        UserStatusKey key = new UserStatusKey();
        BeanUtils.copyProperties(entity, key);

        UserStatus userStatus = super.userStatusMapper.selectByPrimaryKey(key);
        if (userStatus == null) {
            return true;

        } else if (TERMINATED == Status.valueFrom(userStatus.getUserStatusCode())) {
            return true;

        } else {
            return false;
        }
    }

    /**
     * 入力csvの共通商品コードから、キャンペーンIDを返す.
     * 
     * @param commonProductCode 共通商品コード
     * @return キャンペーンID
     */
    private String toCampaignId(String commonProductCode) {
        String campaignId = StringUtils.substringBefore(commonProductCode, "|");

        return StringUtils.trim(campaignId);
    }

    /**
     * 入力csvの共通商品コードから、サービスコードを抜き出す.
     * 
     * @param commonProductCode 共通商品コード
     * @return サービスコード
     */
    private String toServiceCode(String commonProductCode) {
        String serviceCode = StringUtils.substringAfter(commonProductCode, "|");

        return StringUtils.trim(serviceCode);
    }

    /**
     * コードを事業者名に変換する.
     * 
     * @param catvCode CATV事業者コード
     * @return CATV事業者名
     */
    private String toCompanyName(String catvCode) {
        return "JDSテスト局"; // TODO idpdb.CATV_Mテーブル参照.
    }

    /**
     * 入力csvを、入会判定APIのリクエストに変換する.
     * 
     * @param entity サービスプロビジョニングテーブル
     * @return 入会判定APIのリクエスト
     */
    private VerifyRequest toVerifyRequest(ServiceProvisioning entity) {
        String commonProductCode = entity.getCommonProductCode();

        String campaignId = toCampaignId(commonProductCode);
        String serviceCode = toServiceCode(commonProductCode);

        VerifyRequest req = new VerifyRequest(entity.getCableId(), campaignId, entity.getMailAddress(), serviceCode);
        return req;
    }

    /**
     * 新規入会／再入会APIのリクエストを、入会判定APIのリクエストに変換する.
     * 
     * @param req 新規入会／再入会API(入会判定API)のリクエスト
     * @return 入会判定APIのリクエスト
     */
    private VerifyRequest toVerifyRequest(ContractRequest req) {
        VerifyRequest verifyRequest = new VerifyRequest();

        BeanUtils.copyProperties(req, verifyRequest);
        return verifyRequest;
    }

    /**
     * 新規入会／再入会APIのリクエストに変換する.
     * 
     * @param entity サービスプロビジョニングテーブル
     * @return 新規入会／再入会APIのリクエスト
     */
    private ContractRequest toContractRequest(ServiceProvisioning entity) {
        ContractRequest req = new ContractRequest();

        BeanUtils.copyProperties(toVerifyRequest(entity), req);

        req.setLast_name(StringUtils.substringBefore(entity.getFullName(), EM_SPACE));
        req.setFirst_name(StringUtils.substringAfter(entity.getFullName(), EM_SPACE));

        Optional<Gender> gender = BaseCodeIf.getOptional(Gender.class, entity.getGender());
        gender.ifPresent(g -> req.setGender(g.getGender()));

        Optional<Date> birthday = Optional.ofNullable(entity.getBirthday());
        birthday.ifPresent(
                b -> req.setBirthdate(DateFormatUtils.format(b, ISO_8601_EXTENDED_DATE_FORMAT.getPattern())));

        String catvCode = entity.getCatvCode();
        req.setCatv_company_code(catvCode);
        req.setCatv_company_name(toCompanyName(catvCode));

        req.setCatv_personal_id(entity.getPersonalId());
        return req;
    }

    /**
     * 新規入会のリクエストにデフォルト値をセットする.
     * 
     * @param req 新規入会／再入会APIのリクエスト
     */
    private void defaultIfBlank(ContractRequest req) {
        if (ENTRY != req.getEntry_type()) {
            return;
        }
        req.setLast_name(StringUtils.defaultIfBlank(req.getLast_name(), LAST_NAME));
        req.setFirst_name(StringUtils.defaultIfBlank(req.getFirst_name(), FIRST_NAME));

        req.setGender(ObjectUtils.defaultIfNull(req.getGender(), GENDER));
        req.setBirthdate(StringUtils.defaultIfBlank(req.getBirthdate(), BIRTH_DATE));
    }

    /**
     * 入会判定APIをコールし、結果を利用者ステータスと突合せ、Entityに設定する.<br>
     * 契約者を増やす方針の為、利用者ステータス のステータスと矛盾がないかのチェックはせず、<br>
     * Upsert文で、利用者ステータステーブルを更新する.
     * 
     * @param request 新規入会／再入会API(入会判定API)のリクエスト
     * @return 結果コード
     */
    private ResultCode verify(ContractRequest request) {
        VerifyRequest req = toVerifyRequest(request);

        ResponseEntity<VerifyResponse> res = super.postForEntity(verifyUrl, req, VerifyResponse.class);
        ResultCode resultCode = super.toResultCode(res);

        if (SUCCESS != resultCode) {
            return resultCode;
        }
        VerifyResponse result = res.getBody();
        VerifyResultCode jsonCode = result.getResult_code();
        if (jsonCode == null) {
            super.logger.log("SIM.ERROR.9992", "【想定外】処理結果コード(result_code)がレスポンスにありません。");
            return NG_RESPONSE;
        }
        switch (jsonCode) {
        case OK:
            EntryType entryType = result.getEntry_type();
            if (entryType == null) {
                return NG_RESPONSE;
            }
            request.setEntry_type(entryType);
            return SUCCESS;

        case INVALID_CAMPAIGN_ID:
            return NG_INVALID_CAMPAIGN_ID;
        case EMAIL_AND_CABLE_ID_MISMATCHED:
            return NG_EMAIL_AND_CABLE_ID_MISMATCHED;
        case ALREADY_USED_CABLE_ID:
            return NG_ALREADY_USED_CABLE_ID;
        case ALREADY_SUBSCRIBED_OTHER:
            return NG_ALREADY_SUBSCRIBED_OTHER;
        case ALREADY_SUBSCRIBED_CABLE_ID:
            return NG_ALREADY_SUBSCRIBED_CABLE_ID;
        case ALREADY_USED_EMAIL:
            return NG_ALREADY_USED_EMAIL;
        case EMAIL_AND_SERVICE_CODE_MISMATCHED:
            return NG_EMAIL_AND_SERVICE_CODE_MISMATCHED;
        case INTERNAL_ERROR:
            return NG_INTERNAL_ERROR;
        default:
            return NG_RESPONSE;
        }
    }

    /**
     * 新規入会／再入会APIをコールし、結果をEntityに設定する.
     * 
     * @param request 新規入会／再入会API(入会判定API)のリクエスト
     * @param entity  サービスプロビジョニングテーブル
     * @return 結果コード
     */
    private ResultCode contract(ContractRequest request, ServiceProvisioning entity) {
        defaultIfBlank(request);

        ResponseEntity<ContractResponse> res = super.postForEntity(contractUrl, request, ContractResponse.class);
        ResultCode resultCode = super.toResultCode(res);

        if (SUCCESS != resultCode) {
            return resultCode;
        }
        ContractResponse result = res.getBody();
        VerifyResultCode jsonCode = result.getResult_code();
        if (jsonCode == null) {
            super.logger.log("SIM.ERROR.9992", "【想定外】処理結果コード(result_code)がレスポンスにありません。");
            return NG_RESPONSE;
        }
        switch (jsonCode) {
        case OK:
            String spAccountId = result.getAccount_id();
            String spContractId = result.getSubscription_id();

            if (StringUtils.isAnyBlank(spAccountId, spContractId)) {
                return NG_RESPONSE;
            }
            entity.setSpAccountId(spAccountId);
            entity.setSpContractId(spAccountId);

            upsert(entity);
            return SUCCESS;

        case INVALID_CAMPAIGN_ID:
            return NG_INVALID_CAMPAIGN_ID;
        case EMAIL_AND_CABLE_ID_MISMATCHED:
            return NG_EMAIL_AND_CABLE_ID_MISMATCHED;
        case ALREADY_USED_CABLE_ID:
            return NG_ALREADY_USED_CABLE_ID;
        case ALREADY_SUBSCRIBED_OTHER:
            return NG_ALREADY_SUBSCRIBED_OTHER;
        case ALREADY_SUBSCRIBED_CABLE_ID:
            return NG_ALREADY_SUBSCRIBED_CABLE_ID;
        case ALREADY_USED_EMAIL:
            return NG_ALREADY_USED_EMAIL;
        case EMAIL_AND_SERVICE_CODE_MISMATCHED:
            return NG_EMAIL_AND_SERVICE_CODE_MISMATCHED;
        case INTERNAL_ERROR:
            return NG_INTERNAL_ERROR;
        default:
            return NG_RESPONSE;
        }
    }

    /**
     * 利用者ステータステーブルで、Upsert処理をする.
     * 
     * @param entity サービスプロビジョニングテーブル
     */
    private void upsert(ServiceProvisioning entity) {

        UserStatusKey key = new UserStatusKey();
        BeanUtils.copyProperties(entity, key);

        UserStatus userStatus = super.userStatusMapper.selectByPrimaryKey(key);
        if (userStatus == null) {
            userStatus = new UserStatus();
            BeanUtils.copyProperties(entity, userStatus); // 登録/更新日時は nullでコピーされる

            userStatus.setUserStatusCode(Status.NORMAL.getCode());
            super.userStatusMapper.insertSelective(userStatus);

        } else {
            userStatus.setSpContractId(entity.getSpContractId());

            userStatus.setUserStatusCode(Status.NORMAL.getCode());
            super.userStatusMapper.updateByPrimaryKeySelective(userStatus);
        }
    }

}
