package foo.sample.app.json.dev;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import foo.sample.constant.devEnums.EntryType;
import foo.sample.constant.devEnums.VerifyResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入会判定API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VerifyResponse {

    /** ケーブルID */
    private String cable_id;

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
}
