package foo.sample.constant;

import com.fasterxml.jackson.annotation.JsonCreator;

import foo.sample.constant.base.BaseCodeIf;
import lombok.Getter;

/**
 * ケーブルID連携基盤（ケーブルIDプラットフォーム）連携で利用するEnum.
 */
public class CablePlatformEnums {

    /**
     * 性別.
     */
    @Getter
    public enum Gender implements BaseCodeIf<Gender> {
        /** 男性 */
        MALE("1", foo.sample.constant.devEnums.Gender.MALE),
        /** 女性 */
        FEMALE("2", foo.sample.constant.devEnums.Gender.FEMALE),
        /** 不明 */
        NOT_KNOWN("3", foo.sample.constant.devEnums.Gender.NOT_KNOWN),
        /** その他 */
        OTHER("4", foo.sample.constant.devEnums.Gender.NOT_KNOWN);

        private String code;
        private foo.sample.constant.devEnums.Gender gender;

        private Gender(String code, foo.sample.constant.devEnums.Gender gender) {
            this.code = code;
            this.gender = gender;
        }

        /**
         * codeを、大文字小文字を無視して、enumに変換.<br>
         * 
         * ObjectMapper.readValue()で以下の場合、変換エラー.
         * <ul>
         *   <li>code値が、ordinal()とずれてる</li>
         *   <li>code値が、name()ではない</li>
         * </ul>
         * 
         * @param code 性別
         * @return Gender
         */
        @JsonCreator
        public static Gender valueFrom(String code) {
            return BaseCodeIf.getEnum(Gender.class, code);
        }
    }

    /**
     * SMS契約識別情報.
     */
    @Getter
    public enum SmsContractFlag implements BaseCodeIf<SmsContractFlag> {
        /** 加入 */
        JOINED("0"),
        /** 未加入 */
        NOT("1");

        private String code;

        private SmsContractFlag(String code) {
            this.code = code;
        }
    }

    /**
     * 認可ステータス.
     */
    @Getter
    public enum AuthorizeFlag implements BaseCodeIf<AuthorizeFlag> {
        /** 認可OK */
        OK("0"),
        /** 認可NG */
        NG("1");

        private String code;

        private AuthorizeFlag(String code) {
            this.code = code;
        }
    }

    /**
     * SP利用可否.
     */
    @Getter
    public enum EnableFlag implements BaseCodeIf<EnableFlag> {
        /** SP利用可 */
        OK("0"),
        /** SP利用不可 */
        NG("1");

        private String code;

        private EnableFlag(String code) {
            this.code = code;
        }
    }

    /**
     * 使用許諾有無.
     */
    @Getter
    public enum ConsentFlag implements BaseCodeIf<ConsentFlag> {
        /** 有 */
        OK("0"),
        /** 無 */
        NG("1");

        private String code;

        private ConsentFlag(String code) {
            this.code = code;
        }
    }
}
