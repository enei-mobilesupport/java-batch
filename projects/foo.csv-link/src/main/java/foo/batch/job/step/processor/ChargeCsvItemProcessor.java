package foo.sample.batch.job.step.processor;

import static foo.sample.constant.ChargeResult.DATE_FORMAT_ERROR;
import static foo.sample.constant.ChargeResult.UNKNOWN;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.batch.exception.ServiceException;
import foo.sample.batch.service.DailyChargeService;
import foo.sample.component.LogComponent;
import foo.sample.constant.ChargeResult;
import foo.sample.constant.log.OperationId;
import foo.sample.db.auto.model.Charge;
import foo.sample.file.entity.ChargeCsv;

public class ChargeCsvItemProcessor implements ItemProcessor<ChargeCsv, ChargeCsv> {

    @Autowired
    private LogComponent logger;
    @Autowired
    private DailyChargeService dailyChargeService;

    @Override
    public ChargeCsv process(final ChargeCsv csv) {

        try {
            return perform(csv);

        } catch (ServiceException e) {
            csv.setResultCode(e.getErrorCode().getResultCode());
            return csv;

        } catch (Exception e) {
            csv.setResultCode(UNKNOWN.getChargeResultCode());
            return csv;
        }

    }

    /**
     * 日次課金連携を行う.
     * 
     * @param csv CSVファイルの１行
     * @return 結果コードを含む CSVファイルの１行
     * @throws Exception エラー
     */
    private ChargeCsv perform(final ChargeCsv csv) throws Exception {

        String contractId = csv.getSmsContractFlag();
        String spId = csv.getSpId();
        String catvCode = csv.getCatvCode();
        String operation = OperationId.DAILY.toString();
        logger.setTarget(catvCode, spId, operation, contractId);

        Charge chargeEntity = toDailyCharge(csv);

        ChargeResult resultCode = UNKNOWN;
        logger.log("SIM.INFO.0030");
        resultCode = dailyChargeService.charge(chargeEntity);
        csv.setResultCode(resultCode.getChargeResultCode());
        logger.log("SIM.INFO.0031");

        return csv;
    }

    /**
     * csvファイルの１行を課金テーブルのレコードにマッピング.
     * 
     * @param csv      サービスプロバイダ課金連携情報
     * @param fileName 連携ファイル名
     * @param cableId  ケーブルID
     * @return DBのEntity
     */
    private Charge toDailyCharge(final ChargeCsv csv) throws Exception {

        Charge entity = new Charge();
        BeanUtils.copyProperties(csv, entity);

        // key
        entity.setCableId(csv.getCableId());
        entity.setSettlementId(csv.getSpSettlementId());

        // copyされないものを設定
        entity.setSubscriberId(csv.getSmsContractFlag());
        entity.setAmountTaxExcluded(Integer.parseInt(csv.getAmountTaxExcluded()));
        entity.setAmountTaxIncluded(Integer.parseInt(csv.getAmountTaxIncluded()));
        entity.setChargeOn(convertDateFormat(csv.getChargeOn()));
        entity.setServiceEndAt(convertDateFormat(csv.getServiceEndAt()));
        entity.setServiceStartAt(convertDateFormat(csv.getServiceStartAt()));

        return entity;
    }

    private Date convertDateFormat(String strDate) throws Exception {

        Date date = null;
        try {
            date = DateUtils.parseDate(strDate, new String[] { "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd" });
        } catch (ParseException e) {
            logger.log("SIM.ERROR.6002", e, strDate);
            throw new ServiceException(DATE_FORMAT_ERROR, e);
        }

        return date;

    }

}
