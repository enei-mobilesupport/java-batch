package foo.sample.app.json.dev;

import foo.sample.constant.devEnums.EntryType;
import foo.sample.constant.devEnums.Gender;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 入会API.
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(callSuper = false)
public class ContractRequest extends VerifyRequest {

    /** 入会区分 */
    private EntryType entry_type;

    /** 姓 */
    private String last_name;

    /** 名 */
    private String first_name;

    /** 性別 */
    private Gender gender;

    /** 生年月日 */
    private String birthdate;

    /** CATV事業者コード */
    private String catv_company_code;

    /** CATV事業者名 */
    private String catv_company_name;

    /** CATV加入者識別子 */
    private String catv_personal_id;
}
