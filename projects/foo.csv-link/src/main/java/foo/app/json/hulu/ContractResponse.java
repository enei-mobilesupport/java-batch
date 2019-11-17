package foo.sample.app.json.dev;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import foo.sample.constant.devEnums.EntryType;
import foo.sample.constant.devEnums.VerifyResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 新規入会／再入会API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ContractResponse {

    /** ケーブルID */
    private String cable_id;

    /** devアカウントID */
    private String account_id;

    /** dev契約ID */
    private String subscription_id;

    /** 処理結果コード */
    private VerifyResultCode result_code;

    /** エラー */
    private List<Object> errors;

    /** 入会区分 */
    private EntryType entry_type;

    /** 課金額（税込） */
    private Integer tax_included_amount;

    /** 課金額（税抜） */
    private Integer tax_excluded_amount;

    /** 無料期間 */
    private Integer free_period;

    /** 次回課金日 */
    private String next_billing_at;
}
