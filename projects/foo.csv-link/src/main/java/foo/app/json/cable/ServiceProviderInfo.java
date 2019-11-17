package foo.sample.app.json.cable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import foo.sample.constant.CablePlatformEnums.AuthorizeFlag;
import foo.sample.constant.CablePlatformEnums.ConsentFlag;
import foo.sample.constant.CablePlatformEnums.EnableFlag;
import foo.sample.constant.CablePlatformEnums.Gender;
import foo.sample.constant.CablePlatformEnums.SmsContractFlag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * SP情報（個人情報）.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceProviderInfo {

    /** 氏名 */
    private String full_name_kanji;

    /** 氏名カナ */
    private String full_name_kana;

    /** 生年月日 */
    private String birthday;

    /** 性別 */
    private Gender gender;

    /** 郵便番号 */
    private String postal_cd;

    /** 住所 */
    private String address;

    /** メールアドレス */
    private String mail_address;

    /** 世帯ID */
    private String contract_id;

    /** SMS契約識別情報 */
    private SmsContractFlag contract_status;

    /** 認可ステータス */
    @JsonProperty(access = Access.WRITE_ONLY)
    private AuthorizeFlag authz_status;

    /** 利用可否 */
    @JsonProperty(access = Access.WRITE_ONLY)
    private EnableFlag enable;

    /** 使用許諾有無 */
    @JsonProperty(access = Access.WRITE_ONLY)
    private ConsentFlag consent;
}
