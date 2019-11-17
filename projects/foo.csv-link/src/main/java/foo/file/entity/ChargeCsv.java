package foo.sample.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SP課金連携ファイルのEntity.<br>
 * ex. dev_SIM_sales_20200101235959.csv
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ChargeCsv {

    /** 結果コード */
    private String resultCode;
    /** ケーブルID */
    private String cableId;

    /** SP_ID */
    private String spId;
    /** CATV事業者コード */
    private String catvCode;
    /** CATV事業者名 */
    private String catvName;
    /** CATV加入者識別子 */
    private String smsContractFlag;

    /** SP商品コード */
    private String spProductCode;
    /** SP商品名 */
    private String spProductName;

    /** SP契約ID */
    private String spSubscriberId;
    /** SP請求ID */
    private String spSettlementId;

    /** サービス開始日時 */
    private String serviceStartAt;
    /** サービス終了日時 */
    private String serviceEndAt;

    /** 課金日 */
    private String chargeOn;
    /** 課金金額（税抜き） */
    private String amountTaxExcluded;
    /** 課金金額（税込み） */
    private String amountTaxIncluded;

    /** 継続課金フラグ */
    private String chargeType;
}
