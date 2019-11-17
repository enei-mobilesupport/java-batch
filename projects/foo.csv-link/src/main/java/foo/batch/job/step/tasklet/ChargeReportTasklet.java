package foo.sample.batch.job.step.tasklet;

import static org.springframework.batch.repeat.RepeatStatus.FINISHED;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.time.DateUtils;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import foo.sample.batch.job.step.writer.CsvFileItemWriter;
import foo.sample.component.LogComponent;
import foo.sample.db.auto.crud.ChargeMapper;
import foo.sample.db.auto.model.Charge;
import foo.sample.db.auto.model.ChargeExample;
import foo.sample.file.entity.ChargeReportCsv;

/**
 * 課金レポート([CATV-CODE]_[SP_ID]_SIM_settlement_[YYYYMMDDHHMMSS].csv)出力.
 */
public class ChargeReportTasklet implements Tasklet, InitializingBean {

    @Value("${sms.charge.directory}")
    private String directory;

    @Value("${sms.charge.filename}")
    private String filename;

    @Autowired
    private ChargeMapper chargeMapper;

    @Autowired
    private LogComponent logger;

    // @Autowired
    // private CsvFileItemWriter<ChargeReportCsv> csvFileItemWriter;

    @Override
    public void afterPropertiesSet() throws Exception {

        // フォルダの存在チェック
        File folder = new File(directory);
        if (!folder.exists()) {

            folder.mkdir();
        }

    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        ChargeExample chargeEx = new ChargeExample();

      //@formatter:off
        chargeEx.createCriteria()
                .andChargeOnGreaterThanOrEqualTo(firstMonth(true))
                .andChargeOnLessThan(firstMonth(false));
        chargeEx.setOrderByClause("CATV_CODE, SP_ID, SUBSCRIBER_ID");
        //@formatter:on

        List<Charge> chargeList = chargeMapper.selectByExample(chargeEx);

        String catvCode = "";
        String spId = "";
        String subscriberId = "";
        String filePath = "";

        for (Charge charge : chargeList) {
            if (!catvCode.equals(charge.getCatvCode()) || (!spId.equals(charge.getSpId()))) {

                // 出力ファイル名(パス)の生成.
                filePath = createFileName(charge);
            }

            // 重複契約IDのチェック
            chkSubId(charge, catvCode, spId, subscriberId);

            // CSVデータの設定
            ChargeReportCsv chargeReportCsv = new ChargeReportCsv();
            chargeReportCsv = dataSetCsv(charge);

            List<ChargeReportCsv> items = new ArrayList<ChargeReportCsv>();
            BeanUtils.copyProperties(chargeReportCsv, items);

            // 結果を CSVファイル出力する.
            // TODO csvFileItemWriterでの実装が上手くできず、元の出力方式にしています。
            // resultWriter(filePath);
            // csvFileItemWriter.write(items);
            FileWriter fw = new FileWriter(filePath, true);
            printWrite(fw, chargeReportCsv);

            catvCode = charge.getCatvCode();
            spId = charge.getSpId();
            subscriberId = charge.getSubscriberId();

        }
        return FINISHED;

    }

    /**
    CsvFileItemWriter<ChargeReportCsv> resultWriter(String filePath) {

        // @formatter:off
        String[] fieldNames = Arrays.stream(ChargeReportCsv.class.getDeclaredFields())
                .map(Field::getName)
                .toArray(String[]::new);
        // @formatter:on
        return new CsvFileItemWriter<ChargeReportCsv>(directory, filePath, fieldNames);
    }

    /**
     * 前月の月初/当月の月初を取得する
     * 
     * @param firstFlg 前月の月初(True)/当月の月初(False)フラグ
     * @return 時間を切り捨て(00:00:00)した前月の月初/当月の月初を返す
     */
    Date firstMonth(boolean firstFlg) {

        Calendar cal = Calendar.getInstance();
        String yearMonth = new SimpleDateFormat("yyyyMM").format(cal.getTime());

        int year = Integer.parseInt(yearMonth.substring(0, 4));
        int month = Integer.parseInt(yearMonth.substring(4, 6));

        if (firstFlg) {
            month = month - 1;
        }

        cal.set(year, month - 1, 1);
        return DateUtils.truncate(cal.getTime(), Calendar.DAY_OF_MONTH);

    }

    /**
     * ファイル名の生成
     * 
     * @param charge 出力する課金データ
     * @return 出力ファイル名(パス付き)を返す
     */
    String createFileName(Charge charge) {
        String fileName = filename;

        fileName = fileName.replace("[CATV-CODE]", charge.getCatvCode());
        fileName = fileName.replace("[SP_ID]", charge.getSpId());

        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");

        fileName = fileName.replace("[YYYYMMDDHHMMSS]", sdf.format(c.getTime()));
        fileName = directory + fileName + ".csv";
        // fileName = fileName + ".csv";

        // 出力ファイル名(パス付き)の生成.
        return fileName;
    }

    /**
     * CSVデータの設定処理
     * 
     * @param charge 出力する課金データ
     */
    ChargeReportCsv dataSetCsv(Charge charge) {
        ChargeReportCsv reportCsv = new ChargeReportCsv();

        // 契約者ID
        // 金額
        // 継続課金フラグ
        // 結果コード
        // CATV事業者コード
        // SP_ID
        BeanUtils.copyProperties(charge, reportCsv);

        // SP_ID
        BeanUtils.copyProperties(charge, reportCsv);

        // 個人ID
        reportCsv.setPersonId(charge.getSubscriberId());

        // 請求ID
        reportCsv.setSpSettlementId(charge.getSettlementId());

        // 決済日時
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        reportCsv.setChargeOn(sdf.format(charge.getServiceStartAt()));

        // 商品コード
        reportCsv.setProductCode(charge.getSmsProductCode());

        // 商品名称
        reportCsv.setProductName(charge.getSmsProductName());

        return reportCsv;
    }

    /**
     * CSVデータの書き込み処理
     * 
     * @param fw        書き込みファイル
     * @param reportCsv 出力データ
     */
    void printWrite(FileWriter fw, ChargeReportCsv reportCsv) {

        PrintWriter pw = new PrintWriter(new BufferedWriter(fw));

        // 契約者ID
        pw.print(reportCsv.getSubscriberId());
        pw.print(",");

        // 個人ID
        pw.print(reportCsv.getSubscriberId());
        pw.print(",");

        // 請求ID
        pw.print(reportCsv.getSpSettlementId());
        pw.print(",");

        // 決済日時
        pw.print(reportCsv.getChargeOn());
        pw.print(",");

        // 商品コード
        pw.print(reportCsv.getProductCode());
        pw.print(",");

        // 商品名称
        pw.print(reportCsv.getProductName());
        pw.print(",");

        // 金額
        pw.print(reportCsv.getAmountTaxIncluded());
        pw.print(",");

        // 継続課金フラグ
        pw.print(reportCsv.getChargeType());
        pw.print(",");

        // 結果コード
        pw.print(reportCsv.getResultCode());
        pw.print(",");

        // CATV事業者コード
        pw.print(reportCsv.getCatvCode());
        pw.print(",");

        // SP_ID
        pw.print(reportCsv.getSpId());
        pw.println();

        // ファイルに書き出す
        pw.close();
    }

    /**
     * 契約者IDの重複チェック
     * 
     * @param charge       出力する課金データ
     * @param catvCode     CATV事業者コード
     * @param spId         SP_ID
     * @param subscriberId 契約者ID
     */
    void chkSubId(Charge charge, String catvCode, String spId, String subscriberId) {

        //@formatter:off
        if (catvCode.equals(charge.getCatvCode()) 
                && (spId.equals(charge.getSpId()))
                && (subscriberId.equals(charge.getSubscriberId()))) {
         //@formatter:on

            logger.log("SIM.ALERT.0001", catvCode, spId, subscriberId);
        }
    }
}
