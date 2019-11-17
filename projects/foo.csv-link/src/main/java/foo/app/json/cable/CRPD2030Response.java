package foo.sample.app.json.cable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ケーブルID作成（オペレータ向け）.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CRPD2030Response {

    /** CATV事業者コード */
    private String catv_cd;

    /** 個社ID */
    private String catv_personal_id;

    /** SPコード */
    private String service_provider_cd;

    /** ケーブルID */
    private String cable_id;

    /** 結果コード */
    private String result_cd;

    /** 結果メッセージ */
    private String result_msg;
}
