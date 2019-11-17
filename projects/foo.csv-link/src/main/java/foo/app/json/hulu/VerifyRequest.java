package foo.sample.app.json.dev;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 入会判定API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VerifyRequest {

    /** ケーブルID */
    private String cable_id;

    /** キャンペーンID */
    private String campaign_id;

    /** メールアドレス */
    private String email;

    /** サービスコード */
    private String service_code;
}
