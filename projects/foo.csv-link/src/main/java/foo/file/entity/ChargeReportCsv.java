package foo.sample.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SMS課金連携ファイルのEntity.<br>
 * ex. CATV0008_dev_SIM_settlement_20200101235959.csv
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargeReportCsv {

    /** 契約者ID */
    private String subscriberId;
    /** 個人ID */
    private String personId;

    /** SP請求ID */
    private String spSettlementId;
    /** 決済日時(課金日) */
    private String chargeOn;

    /** 商品コード */
    private String productCode;
    /** 商品名 */
    private String productName;
    /** 金額（税込み） */
    private String amountTaxIncluded;

    /** 継続課金フラグ */
    private String chargeType;
    /** 結果コード */
    private String resultCode;

    /** SP_ID */
    private String spId;
    /** CATV事業者コード */
    private String catvCode;
}
