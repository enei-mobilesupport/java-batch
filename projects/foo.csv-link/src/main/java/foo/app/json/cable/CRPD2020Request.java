package foo.sample.app.json.cable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ケーブルID取得（オペレータ向け）.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CRPD2020Request {

    /** CATV事業者コード */
    private String catv_cd;

    /** 個社ID */
    private String catv_personal_id;

    /** SPコード */
    private String service_provider_cd;

    /** オペレータコード */
    private String operator_cd;
}
