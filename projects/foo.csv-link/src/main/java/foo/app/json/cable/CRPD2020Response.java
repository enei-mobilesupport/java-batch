package foo.sample.app.json.cable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ケーブルID取得（オペレータ向け）.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CRPD2020Response {

    /** CATV事業者コード */
    private String catv_cd;

    /** 個社ID */
    private String catv_personal_id;

    /** SPコード */
    private String service_provider_cd;

    /** ケーブルID */
    private String cable_id;

    /** SP情報 */
    private ServiceProviderInfo service_provider_info;

    /** J-LIS情報 */
    private JLisInfo jlis_info;

    /**
     * J-LIS情報.
     */
    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class JLisInfo {

        /** 氏名 */
        private String name;

        /** 生年月日 */
        private String birthday;

        /** 性別 */
        private String gender;

        /** 住所 */
        private String address;
    }
}
