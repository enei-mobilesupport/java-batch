package foo.sample.app.json.dev;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import foo.sample.constant.devEnums.CancelResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dev 解約予約取消API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminateCancelResponse {

    /** 結果コード */
    private CancelResultCode result_code;

    /** 次回決済日 */
    private Date next_billing_at;
}
