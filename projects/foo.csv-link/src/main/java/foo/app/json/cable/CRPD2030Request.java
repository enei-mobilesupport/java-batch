package foo.sample.app.json.cable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * ケーブルID作成（オペレータ向け）.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class CRPD2030Request extends CRPD2020Request {

    /** SP情報 */
    private ServiceProviderInfo service_provider_info;
}
