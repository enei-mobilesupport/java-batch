package foo.sample.file.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SMSプロビジョニング連携ファイルのEntity.<br>
 * ex. CATV0008_SIM_contract_20200101235959.csv
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProvisioningCsv {

    /** 契約者ID */
    private String subscriberId;
    /** 操作区分 */
    private String operateType;

    /** CATV事業者コード */
    private String catvCode;
    /** 個人ID（共通商品CD） */
    private String personalId;

    /** ログインID */
    private String loginId;
    /** パスワード */
    private String password;
    /** 契約者フラグ */
    private String subscriberFlag;
    /** 利用者生年月日 */
    private String birthday;
    /** 成人コンテンツ利用制限 */
    private String adultContentsRegulation;
    /** 共通商品コード */
    private String commonProductCode;
    /** 性別 */
    private String gender;

    /** メールアドレス */
    private String mailAddress;
    /** 郵便番号 */
    private String postalCode;
    /** 休止フラグ */
    private String suspendFlag;

    /** 購入上限額 */
    private String purchaseLimit;
    /** SP_ID */
    private String spId;
    /** 指定期日 */
    private String dueDate;

    /** 結果コード */
    private String resultCode;
}
