package foo.sample.batch.service;

import static foo.sample.constant.ChargeResult.CHARGE_ERROR;
import static foo.sample.constant.ChargeResult.CHARGE_NG;
import static foo.sample.constant.ChargeResult.NOT_CHARGE;
import static foo.sample.constant.ChargeResult.NO_SUCH_CABLE_ID;
import static foo.sample.constant.ChargeResult.OK;
import static foo.sample.constant.ChargeResult.SUCCESS;
import static foo.sample.constant.ChargeResult.UNKNOWN;
import static foo.sample.constant.Status.NORMAL;
import static foo.sample.constant.Status.SUSPENDED;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import foo.sample.component.LogComponent;
import foo.sample.constant.ChargeResult;
import foo.sample.constant.Status;
import foo.sample.db.auto.crud.ChargeMapper;
import foo.sample.db.auto.crud.UserStatusMapper;
import foo.sample.db.auto.model.Charge;
import foo.sample.db.auto.model.UserStatus;
import foo.sample.db.auto.model.UserStatusKey;
import foo.sample.db.custom.ref.crud.ProductMasterMapper;
import foo.sample.db.custom.ref.model.SmsProduct;

/**
 * 日次課金連携
 */
@Service
public class DailyChargeService {

    @Autowired
    private LogComponent logger;
    @Autowired
    private ChargeMapper chargeMapper;
    @Autowired
    private UserStatusMapper userStatusMapper;
    @Autowired
    private ProductMasterMapper productMasterMapper;

    private Resource resourceFile;

    public DailyChargeService(Resource file) {
        this.resourceFile = file;
    }

    /**
     * 日次課金連携 サービスプロバイダから連携された請求ファイル内の該当利用者の利用者ステータスを参照し、請求可否を判定する。
     * また、サービスプロバイダの商品コードからケーブル局が設定している商品コード、商品名を取得する
     * 
     * @param chargeEntity
     * @return 処理結果 請求可能 請求不可 その他エラー（請求不可扱い）
     */
    public ChargeResult charge(Charge chargeEntity) {
        ChargeResult resultCode = UNKNOWN;

        chargeEntity.setSpLinkFileName(resourceFile.getFilename());

        logger.log("SIM.INFO.0021", chargeEntity.getCableId(), chargeEntity.getSpId());
        UserStatus userStatus = getUserStatus(chargeEntity.getCableId(), chargeEntity.getSpId());
        if (userStatus == null) {
            logger.log("SIM.ERROR.6001", chargeEntity.getCableId(), chargeEntity.getSpId());
            chargeEntity.setResultCode(CHARGE_ERROR);
            resultCode = NO_SUCH_CABLE_ID;

        } else {
            String statusCode = userStatus.getUserStatusCode();
            logger.log("SIM.INFO.0022", Status.valueFrom(statusCode), statusCode);
            Status status = Status.valueFrom(statusCode);
            if (status == NORMAL) {
                // 請求可
                chargeEntity.setResultCode(OK);
                resultCode = SUCCESS;
            } else if (status == SUSPENDED) {
                // 請求不可
                chargeEntity.setResultCode(CHARGE_NG);
                resultCode = NOT_CHARGE;

            } else {
                // 請求不可
                chargeEntity.setResultCode(CHARGE_ERROR);

            }
        }

        String productCode = chargeEntity.getSpProductCode();
        logger.log("SIM.INFO.0034", productCode);

        SmsProduct smsProduct = getProductMaster(productCode);
        String smsProductCode = smsProduct.getProductCode();
        String smsProductName = smsProduct.getProductName();
        logger.log("SIM.INFO.0035", smsProductCode, smsProductName);

        chargeEntity.setSmsProductCode(smsProductCode);
        chargeEntity.setSmsProductName(smsProductName);
        
        saveCharge(chargeEntity);

        return resultCode;
    }

    private UserStatus getUserStatus(String cableId, String spId) {

        UserStatusKey key = new UserStatusKey();
        key.setCableId(cableId);
        key.setSpId(spId);
        UserStatus userStatus = userStatusMapper.selectByPrimaryKey(key);
        return userStatus;
    }

    private void saveCharge(Charge charge) {
        charge.setInsertAt(new Date());
        charge.setUpdateAt(new Date());
        chargeMapper.insertSelective(charge);
    }

    private SmsProduct getProductMaster(String code) {
        SmsProduct smsProduct = productMasterMapper.findOne(code);
        return smsProduct;
    }
}
