package foo.sample.constant;

import foo.sample.constant.base.BaseCodeIf;
import lombok.Getter;

/**
 * dev連携で利用するEnum.
 */
public class devEnums {

    /**
     * 性別.
     */
    @Getter
    public enum Gender implements BaseCodeIf<Gender> {
        /** 男性 */
        MALE("MALE"),
        /** 女性 */
        FEMALE("FEMALE"),
        /** 答えない */
        NOT_KNOWN("NOT_KNOWN");

        private String code;

        private Gender(String code) {
            this.code = code;
        }
    }

    /**
     * 入会区分.
     */
    @Getter
    public enum EntryType implements BaseCodeIf<EntryType> {
        /** 新規入会 */
        ENTRY("ENTRY"),
        /** 再入会 */
        REENTRY("REENTRY");

        private String code;

        private EntryType(String code) {
            this.code = code;
        }
    }

    /**
     * 入会判定APIと入会APIの結果コード（入会可否）.
     */
    @Getter
    public enum VerifyResultCode implements BaseCodeIf<VerifyResultCode> {
        /** OK */
        OK("OK"),
        /** キャンペーンID不正 */
        INVALID_CAMPAIGN_ID("INVALID_CAMPAIGN_ID"),
        /** メールアドレスとケーブルIDの組み合わせが異なる（既存メアドと既存ケーブルID） */
        EMAIL_AND_CABLE_ID_MISMATCHED("EMAIL_AND_CABLE_ID_MISMATCHED"),
        /** メールアドレスとケーブルIDの組み合わせが異なる（新メアドと既存ケーブルID） */
        ALREADY_USED_CABLE_ID("ALREADY_USED_CABLE_ID"),
        /** ケーブルID決済以外で契約済み */
        ALREADY_SUBSCRIBED_OTHER("ALREADY_SUBSCRIBED_OTHER"),
        /** ケーブルID決済で契約済み */
        ALREADY_SUBSCRIBED_CABLE_ID("ALREADY_SUBSCRIBED_CABLE_ID"),
        /** メールアドレスが登録済み */
        ALREADY_USED_EMAIL("ALREADY_USED_EMAIL"),
        /** メールアドレスとサービスコードの組み合わせが異なる */
        EMAIL_AND_SERVICE_CODE_MISMATCHED("EMAIL_AND_SERVICE_CODE_MISMATCHED"),
        /** 内部エラー */
        INTERNAL_ERROR("INTERNAL_ERROR");

        private String code;

        private VerifyResultCode(String code) {
            this.code = code;
        }
    }

    /**
     * 解約区分.
     */
    @Getter
    public enum CancelType implements BaseCodeIf<CancelType> {
        /** 即時解約 */
        CANCEL("CANCEL"),
        /** 解約予約 */
        PENDING("PENDING");

        private String code;

        private CancelType(String code) {
            this.code = code;
        }
    }

    /**
     * 解約APIの結果コード（解約可否）.
     */
    @Getter
    public enum CancelResultCode implements BaseCodeIf<CancelResultCode> {
        /** OK */
        OK("OK"),
        /** 解約済みのため、即時解約不可。解約済みのため、解約予約不可。 */
        ALREADY_CANCELED("ALREADY_CANCELED"),
        /** 解約予約済みのため、解約予約不可 */
        ALREADY_PENDING("ALREADY_PENDING"),
        /** 決済依頼中のため、即時解約不可。決済依頼中のため、解約予約不可 */
        BILLING_HOLD("BILLING_HOLD"),
        /** 即時解約ができないアカウントステータス。解約予約ができないアカウントステータス */
        INVALID_ACCOUNT_STATUS("INVALID_ACCOUNT_STATUS"),
        /** ケーブルID決済以外で契約済み */
        ALREADY_SUBSCRIBED_OTHER("ALREADY_SUBSCRIBED_OTHER"),
        /** 未知のケーブルIDを指定した場合 */
        INVALID_CABLE_ID("INVALID_CABLE_ID"),
        /** 内部エラー */
        INTERNAL_ERROR("INTERNAL_ERROR");

        private String code;

        private CancelResultCode(String code) {
            this.code = code;
        }
    }

}
