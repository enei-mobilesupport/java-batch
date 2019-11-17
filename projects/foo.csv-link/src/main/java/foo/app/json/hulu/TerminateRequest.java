package foo.sample.app.json.dev;

import foo.sample.constant.devEnums.CancelType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dev 解約API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TerminateRequest {

    /** ケーブルID */
    private String cable_id;

    /** 解約区分 */
    private CancelType cancel_type;
}
