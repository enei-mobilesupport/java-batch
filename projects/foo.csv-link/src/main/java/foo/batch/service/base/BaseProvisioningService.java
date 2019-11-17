package foo.sample.batch.service.base;

import static foo.sample.constant.ResultCode.NG_400_BAD_REQUEST;
import static foo.sample.constant.ResultCode.NG_404_NOT_FOUND;
import static foo.sample.constant.ResultCode.NG_500_INTERNAL_SERVER_ERROR;
import static foo.sample.constant.ResultCode.NG_503_SERVICE_UNAVAILABLE;
import static foo.sample.constant.ResultCode.NG_RESPONSE;
import static foo.sample.constant.ResultCode.SUCCESS;
import static foo.sample.constant.ResultCode.UNKNOWN;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import foo.sample.app.config.RestTemplateBuilderSupplier;
import foo.sample.component.LogComponent;
import foo.sample.constant.ResultCode;
import foo.sample.constant.SP;
import foo.sample.constant.Status;
import foo.sample.db.auto.crud.ProvisioningLogMapper;
import foo.sample.db.auto.crud.ServiceProvisioningMapper;
import foo.sample.db.auto.crud.UserStatusMapper;
import foo.sample.db.auto.model.ProvisioningLog;
import foo.sample.db.auto.model.ServiceProvisioning;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusExample;
import foo.sample.db.auto.model.UserStatusKey;

/**
 * Provisioning業務(@Service)のベース.
 */
public abstract class BaseProvisioningService implements InitializingBean {

    @Autowired
    protected LogComponent logger;

    @Autowired
    protected UserStatusMapper autowiredMapper;
    protected UserStatusMapper userStatusMapper = new ProxyMapper();

    @Autowired
    private ServiceProvisioningMapper serviceProvisioningMapper;

    @Autowired
    private ProvisioningLogMapper provisioningLogMapper;

    @Autowired
    private RestTemplateBuilderSupplier restTemplateBuilderSupplier;

    protected RestTemplate restTemplate;

    @Override
    public void afterPropertiesSet() throws Exception {
        // @formatter:off
        restTemplate = restTemplateBuilderSupplier
                .Initialize(SP.dev)
                .get()
                .build();
        // @formatter:on
    }

    /**
     * Provisioning業務を実行.
     * 
     * @param provisionEntity サービスプロビジョニングテーブル
     * @param logEntity       プロビログテーブル
     * @return 結果コード
     * @see foo.sample.constant.Provisioning
     */
    abstract public ResultCode provision(ServiceProvisioning provisionEntity, ProvisioningLog logEntity);

    /**
     * Provisioning業務を実行(開始/終了ログ出力する).
     * 
     * @param provisionEntity サービスプロビジョニングテーブル
     * @param logEntity       プロビログテーブル
     * @return 結果コード
     */
    public ResultCode provisioning(ServiceProvisioning provisionEntity, ProvisioningLog logEntity) {
        String operation = logger.getProvisioning().getNote();

        logger.log("SIM.INFO.0013", operation);
        ResultCode resultCode = provision(provisionEntity, logEntity);

        logger.log("SIM.INFO.0014", operation);
        return resultCode;
    };

    /**
     * Provisioningの履歴を残す.
     * 
     * @param provisionEntity provision結果セット済のサービスプロビジョニングテーブル
     * @param logEntity       provision結果セット済のプロビログテーブル
     * @param resultCode      結果コード
     */
    protected void history(ServiceProvisioning provisionEntity, ProvisioningLog logEntity, ResultCode resultCode) {

        resultCode = defaultIfNull(resultCode, UNKNOWN);
        String dbCode = resultCode.getCode();

        provisionEntity.setResultCode(dbCode);
        logEntity.setResult(dbCode);

        serviceProvisioningMapper.insertSelective(provisionEntity);
        logger.log("SIM.INFO.0028");

        logEntity.setSeqno(provisionEntity.getSeqno());
        provisioningLogMapper.insertSelective(logEntity);
        logger.log("SIM.INFO.0029");
    }

    /**
     * dev APIをコールする.<br>
     * {@link RestTemplate#postForEntity(java.net.URI, Object, Class)} のWrapper.
     * 
     * @param url          エンドポイント
     * @param request      入力JSON
     * @param responseType 出力JSONの型
     * @return API実行結果のレスポンス
     */
    public <T> ResponseEntity<T> postForEntity(String url, Object request, Class<T> responseType) {
        logger.log("SIM.INFO.0121", logger.getProvisioning().getNote(), request);

        return restTemplate.postForEntity(url, request, responseType);
    }

    /**
     * HTTP status codeを devのエラーコードに変換.
     * 
     * @param entity APIコールのレスポンス
     * @return 結果コード
     */
    protected ResultCode toResultCode(ResponseEntity<?> entity) {
        ResultCode resultCode = UNKNOWN;
        logger.log(entity);

        if (entity == null || entity.getStatusCode() == null) {
            resultCode = NG_RESPONSE;
            logger.log("SIM.ERROR.4001", logger.getProvisioning().getNote(), resultCode, resultCode.getCode());
            logger.log("SIM.ERROR.9992", "タイムアウトの可能性があります。");
            return resultCode;
        }
        HttpStatus status = entity.getStatusCode();
        switch (status) {
        case OK:
            resultCode = SUCCESS;
            logger.log("SIM.INFO.0122", logger.getProvisioning().getNote(), resultCode, resultCode.getCode());
            return resultCode;
        case BAD_REQUEST:
            resultCode = NG_400_BAD_REQUEST;
            break;
        case NOT_FOUND:
            resultCode = NG_404_NOT_FOUND;
            break;
        case INTERNAL_SERVER_ERROR:
            resultCode = NG_500_INTERNAL_SERVER_ERROR;
            break;
        case SERVICE_UNAVAILABLE:
            resultCode = NG_503_SERVICE_UNAVAILABLE;
            break;
        default:
            resultCode = NG_RESPONSE;
        }
        logger.log("SIM.ERROR.4001", logger.getProvisioning().getNote(), resultCode, resultCode.getCode());
        return resultCode;
    }

    /**
     * UserStatusMapperの Wrapper.<br>
     * 子クラス(@Service)での Updateで、更新日時カラム設定を省略可にする.<br>
     * 加えて、INFOログ出力を行う.
     */
    private class ProxyMapper implements UserStatusMapper {

        @Override
        public long countByExample(UserStatusExample example) {
            return autowiredMapper.countByExample(example);
        }

        @Override
        public int deleteByExample(UserStatusExample example) {
            return autowiredMapper.deleteByExample(example);
        }

        @Override
        public int deleteByPrimaryKey(UserStatusKey key) {
            return autowiredMapper.deleteByPrimaryKey(key);
        }

        @Override
        public int insert(UserStatus record) {
            setInsertAt(record);

            int count = autowiredMapper.insert(record);
            logger.log("SIM.INFO.0024");
            return count;
        }

        @Override
        public int insertSelective(UserStatus record) {
            setInsertAt(record);

            int count = autowiredMapper.insertSelective(record);
            logger.log("SIM.INFO.0024");
            return count;
        }

        @Override
        public List<UserStatus> selectByExample(UserStatusExample example) {
            return autowiredMapper.selectByExample(example);
        }

        @Override
        public UserStatus selectByPrimaryKey(UserStatusKey key) {

            key = defaultIfNull(key, new UserStatusKey());
            logger.log("SIM.INFO.0021", key.getCableId(), key.getSpId());

            UserStatus userStatus = autowiredMapper.selectByPrimaryKey(key);
            userStatus = clone(userStatus);

            Status status = Status.valueFrom(defaultIfNull(userStatus, new UserStatus()).getUserStatusCode());
            logger.log("SIM.INFO.0022", status.name(), status.getCode());

            return userStatus;
        }

        @Override
        public int updateByExampleSelective(UserStatus record, UserStatusExample example) {
            setUpdateAt(record);
            return autowiredMapper.updateByExampleSelective(record, example);
        }

        @Override
        public int updateByExample(UserStatus record, UserStatusExample example) {
            setUpdateAt(record);
            return autowiredMapper.updateByExample(record, example);
        }

        @Override
        public int updateByPrimaryKeySelective(UserStatus record) {
            setUpdateAt(record);

            int count = autowiredMapper.updateByPrimaryKeySelective(record);
            logger.log("SIM.INFO.0024");
            return count;
        }

        @Override
        public int updateByPrimaryKey(UserStatus record) {
            setUpdateAt(record);

            int count = autowiredMapper.updateByPrimaryKey(record);
            logger.log("SIM.INFO.0024");
            return count;
        }

        /**
         * Objectをディープコピーする.
         * 
         * @param object
         * @return ディープコピーされたobject
         */
        private UserStatus clone(UserStatus object) {
            if (object == null) {
                return null;
            }
            UserStatus userStatus = new UserStatus();
            BeanUtils.copyProperties(object, userStatus);

            return userStatus;
        }

        /**
         * 登録日時カラムに、現在日時を設定.
         * 
         * @param record 利用者ステータステーブル
         */
        private void setInsertAt(UserStatus record) {
            if (record == null) {
                logger.log("SIM.INFO.0023", "null", "null", "null", "null");
                return;
            }
            record.setInsertAt(new Date());
            record.setUpdateAt(new Date());

            Status status = Status.valueFrom(defaultIfNull(record, new UserStatus()).getUserStatusCode());
            logger.log("SIM.INFO.0023", "null", "null", status.name(), status.getCode());
        }

        /**
         * 更新日時カラムに、現在日時を設定.
         * 
         * @param record 利用者ステータステーブル
         */
        private void setUpdateAt(UserStatus record) {
            if (record == null) {
                logger.log("SIM.INFO.0023", "null", "null", "null", "null");
                return;
            }
            UserStatus beforeUpdate = autowiredMapper.selectByPrimaryKey(record);
            Status before = Status.valueFrom(defaultIfNull(beforeUpdate, new UserStatus()).getUserStatusCode());

            record.setUpdateAt(new Date());
            Status after = Status.valueFrom(record.getUserStatusCode());
            logger.log("SIM.INFO.0023", before.name(), before.getCode(), after.name(), after.getCode());
        }
    }

}
