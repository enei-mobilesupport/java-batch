package foo.sample.batch.service;

import static foo.sample.constant.Provisioning.CONTRACT;
import static foo.sample.constant.Provisioning.UNKNOWN;
import static foo.sample.constant.ResultCode.BAD_404_NOT_FOUND;
import static foo.sample.constant.ResultCode.BAD_412_PRECONDITION_FAILED;
import static foo.sample.constant.ResultCode.BAD_503_SERVICE_UNAVAILABLE;
import static foo.sample.constant.ResultCode.BAD_ID_REGISTER;
import static foo.sample.constant.ResultCode.BAD_PARSE_ERROR;
import static foo.sample.constant.ResultCode.BAD_PROVISIONING_TYPE;
import static foo.sample.constant.ResultCode.BAD_RESPONSE;
import static foo.sample.constant.ResultCode.INVALID_PROVISIONING_TYPE;
import static foo.sample.constant.ResultCode.SUCCESS;

import java.io.IOException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;

import foo.sample.app.config.RestTemplateBuilderSupplier;
import foo.sample.app.json.cable.CRPD2020Request;
import foo.sample.app.json.cable.CRPD2020Response;
import foo.sample.app.json.cable.CRPD2030Request;
import foo.sample.app.json.cable.CRPD2030Response;
import foo.sample.app.json.cable.ServiceProviderInfo;
import foo.sample.batch.exception.ServiceException;
import foo.sample.component.LogComponent;
import foo.sample.constant.CablePlatformEnums.Gender;
import foo.sample.constant.Provisioning;
import foo.sample.constant.ResultCode;
import foo.sample.constant.base.BaseCodeIf;
import foo.sample.file.entity.ProvisioningCsv;

/**
 * ケーブルID連携基盤（ケーブルIDプラットフォーム）連携.
 */
@Service
public class CablePlatformService implements InitializingBean {

    @Value("${api.platform.endpoint.cableid.get}")
    private String urlForConvert;
    @Value("${api.platform.endpoint.cableid.put}")
    private String urlForRegister;

    @Value("${api.dev.code.sp}")
    private String spCode;
    @Value("${api.platform.code.op:sample}")
    private String opCode;

    @Autowired
    private LogComponent logger;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private RestTemplateBuilderSupplier restTemplateBuilderSupplier;

    private RestTemplate restTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        // @formatter:off
        restTemplate = restTemplateBuilderSupplier
                .Initialize(null)
                .get()
                .build();
        // @formatter:on
    }

    /**
     * ケーブルID取得API をコールし、ケーブルIDおよび個人情報を取得する.<br>
     * 操作区分：契約申込の場合のみ、ケーブルID取得できなかったら、作成する.
     * 
     * @param csv サービスプロビジョニングCSVファイルの１行
     * @return ケーブルID取得APIのレスポンス
     */
    public CRPD2020Response fetchCableId(final ProvisioningCsv csv) {
        Provisioning operation = logger.getProvisioning();

        logger.log("SIM.INFO.0011");
        CRPD2020Response res = fetchCableId(csv, operation);

        logger.log("SIM.INFO.0012");
        return res;
    }

    /**
     * public CRPD2020Response fetchCableId(csv) の実処理.
     * 
     * @param csv サービスプロビジョニングCSVファイルの１行
     * @return ケーブルID取得APIのレスポンス
     */
    private CRPD2020Response fetchCableId(final ProvisioningCsv csv, Provisioning operation) {

        if (UNKNOWN == operation) {
            throw new ServiceException(INVALID_PROVISIONING_TYPE);
        }
        CRPD2020Response res = convert(csv.getCatvCode(), csv.getSubscriberId());
        if (res != null && StringUtils.hasText(res.getCable_id())) {
            return res;
        }
        if (CONTRACT != operation) {
            throw new ServiceException(BAD_PROVISIONING_TYPE);
        }
        register(csv);
        res = convert(csv.getCatvCode(), csv.getSubscriberId());

        if (res == null || !StringUtils.hasText(res.getCable_id())) {
            throw new ServiceException(BAD_RESPONSE);
        }
        return res;
    }

    /**
     * ケーブルID取得API をコールする.
     * 
     * @param catvCd     CATV事業者コード
     * @param personalId 個社ID
     * @return レスポンス
     */
    private CRPD2020Response convert(String catvCd, String personalId) {
        CRPD2020Request req = new CRPD2020Request(catvCd, personalId, spCode, opCode);
        /** API名 */
        final String API_ID = "CRPD2020";

        logger.log("SIM.INFO.0111", API_ID, req);
        ResponseEntity<String> res = restTemplate.postForEntity(urlForConvert, req, String.class);
        ResultCode resultCode = toResultCode(res);

        if (SUCCESS != resultCode) {
            logger.log("SIM.ERROR.3001", API_ID, resultCode, resultCode.getCode());
            throw new ServiceException(resultCode);
        }
        String json = res.getBody();
        json = StringUtils.replace(json, "\"\"", "null");
        try {
            logger.log("SIM.INFO.0112", API_ID, SUCCESS, SUCCESS.getCode());
            return objectMapper.readValue(json, CRPD2020Response.class);

        } catch (IOException e) {
            logger.log("SIM.ERROR.9991", e);
            logger.log("SIM.ERROR.3001", API_ID, BAD_PARSE_ERROR, BAD_PARSE_ERROR.getCode());
            return new CRPD2020Response();
        }
    }

    /**
     * ケーブルID作成API をコールする.
     * 
     * @param csv サービスプロビジョニングCSVファイルの１行
     */
    private void register(ProvisioningCsv csv) {
        CRPD2030Request req = toCRPD2030Request(csv);
        /** API名 */
        final String API_ID = "CRPD2030";

        logger.log("SIM.INFO.0111", API_ID, req);
        ResponseEntity<CRPD2030Response> res = restTemplate.postForEntity(urlForRegister, req, CRPD2030Response.class);
        ResultCode resultCode = toResultCode(res);

        if (SUCCESS != resultCode) {
            logger.log("SIM.ERROR.3001", API_ID, resultCode, resultCode.getCode());
            throw new ServiceException(resultCode);
        }
        CRPD2030Response response = res.getBody();
        if (!"0".equals(response.getResult_cd())) {

            logger.log("SIM.ERROR.3003");
            throw new ServiceException(BAD_ID_REGISTER);
        }
        logger.log("SIM.INFO.0112", API_ID, SUCCESS, SUCCESS.getCode());
    }

    /**
     * HTTP status codeを ケーブルID連携基盤のエラーコードに変換.
     * 
     * @param entity APIコールのレスポンス
     * @return 結果コード
     */
    private ResultCode toResultCode(ResponseEntity<?> entity) {
        if (entity == null || entity.getStatusCode() == null) {
            return BAD_RESPONSE;
        }
        logger.log(entity);

        HttpStatus status = entity.getStatusCode();
        switch (status) {
        case OK:
            return SUCCESS;
        case PRECONDITION_FAILED:
            return BAD_412_PRECONDITION_FAILED;
        case NOT_FOUND:
            return BAD_404_NOT_FOUND;
        case SERVICE_UNAVAILABLE:
            return BAD_503_SERVICE_UNAVAILABLE;
        default:
            return BAD_RESPONSE;
        }
    }

    /**
     * ケーブルID作成APIのリクエストに、入力csvを変換する.
     * 
     * @param csv サービスプロビジョニングCSVファイルの１行
     * @return ケーブルID作成APIのリクエスト
     */
    private CRPD2030Request toCRPD2030Request(ProvisioningCsv csv) {
        CRPD2020Request base = new CRPD2020Request(csv.getCatvCode(), csv.getSubscriberId(), spCode, opCode);

        CRPD2030Request req = new CRPD2030Request();
        BeanUtils.copyProperties(base, req);

        ServiceProviderInfo personalInfo = new ServiceProviderInfo();
        personalInfo.setBirthday(csv.getBirthday());
        personalInfo.setGender(BaseCodeIf.getEnum(Gender.class, csv.getGender()));
        personalInfo.setPostal_cd(csv.getPostalCode());
        personalInfo.setMail_address(csv.getMailAddress());

        req.setService_provider_info(personalInfo);
        return req;
    }

}
