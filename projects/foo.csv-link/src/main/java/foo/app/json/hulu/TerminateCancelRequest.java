package foo.sample.app.json.dev;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * dev 解約予約取消API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TerminateCancelRequest {

    /** ケーブルID */
    private String cable_id;
}
