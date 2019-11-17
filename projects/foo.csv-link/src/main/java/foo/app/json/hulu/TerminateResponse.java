package foo.sample.app.json.dev;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import foo.sample.constant.devEnums.CancelResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dev 解約API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TerminateResponse {

    /** 結果コード */
    private CancelResultCode result_code;

    /** 解約予定日 */
    private Date cancel_at;
}
