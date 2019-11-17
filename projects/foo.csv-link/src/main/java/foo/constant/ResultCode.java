package foo.sample.constant;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * SERVICE_PROVISIONINGテーブルに登録する結果コード.<br>
 * TODO エラーコード設計. エラーメッセージ出力(バリデーション).
 * <ul>
 * <li>ERROR_: 全体的なエラー.</li>
 * <li>INVALID_:１行ごとのエラー.</li>
 * <li>BAD_:ケーブルID連携基盤 APIコールに伴うエラー.</li>
 * <li>NG_:dev APIコールに伴うエラー.</li>
 * </ul>
 */
@Getter
public enum ResultCode {
    /** 正常終了 */
    SUCCESS("0000"),
    /** application.properties等の設定エラー */
    ERROR_SETTING("0001"),
    /** ファイル読み込みエラー */
    ERROR_FILE_READ("1000"),
    /** 文字コード不正 */
    ERROR_CHARACTER_CODE("1001"),
    /** CSVフォーマットエラー */
    ERROR_CSV_FORMAT("1002"),
    /** ファイル書き込みエラー */
    ERROR_FILE_WRITE("1009"),
    /** API利用不可 */
    ERROR_API_CALL("4000"),
    /** 列数不正 */
    INVALID_COLUMN_NUMBER("1003"),
    /** 必須入力エラー */
    INVALID_REQUIRED_ITEM("1004"),
    /** 契約者ID不正 */
    INVALID_SUBSCRIBER_ID("1101"),
    /** Provisioningタイプ不正 */
    INVALID_PROVISIONING_TYPE("1102"),
    /** CATV事業者コード不正 */
    INVALID_CATV_CODE("1103"),
    /** 商品コード不正 */
    INVALID_PRODUCT_CODE("1104"),
    /** 個人ID不正 */
    INVALID_PERSONAL_ID("1105"),
    /** 休止フラグ不正 */
    INVALID_SUSPEND_FLAG("1106"),
    /** SP_ID不正 */
    INVALID_SP_ID("1107"),
    /** 指定期日不正 */
    INVALID_DUEDATE("1108"),
    /** CATV事業者コード不正(マスタに無い) */
    INVALID_CATV_CODE_ON_MASTER("1200"),
    /** 商品コード不正(マスタに無い) */
    INVALID_PRODUCT_CODE_ON_MASTER("1201"),
    /** キャンペーンID不正(マスタ不整合) */
    INVALID_CAMPAIGN_ID_ON_MASTER("1202"),
    /** ケーブルID取得エラー */
    INVALID_INPUT_FOR_CABLE_ID("1300"),
    /** ユーザステータステーブルに未登録 */
    INVALID_USER("2000"),
    /** 利用者ステータス不正（変更不可のステータスへ更新する操作。例：利用停止中→利用停止） */
    INVALID_USER_STATUS("2001"),
    /** ケーブルIDプラットフォームの API応答エラー（汎用） */
    BAD_RESPONSE("3001"),
    /** 型変換エラー(String->Date等) */
    BAD_PARSE_ERROR("3002"),
    /** Precondition Failed */
    BAD_412_PRECONDITION_FAILED("3412"),
    /** Not Found */
    BAD_404_NOT_FOUND("3404"),
    /** Internal Server Error */
    BAD_500_INTERNAL_SERVER_ERROR("3500"),
    /** Service Unavailable */
    BAD_503_SERVICE_UNAVAILABLE("3503"),
    /** 「契約申込」以外で、ケーブルIDが見つからない */
    BAD_PROVISIONING_TYPE("3002"),
    /** ケーブルID登録エラー */
    BAD_ID_REGISTER("3003"),
    /** devの API応答エラー（汎用） */
    NG_RESPONSE("4001"),
    /** 型変換エラー(String->Date等) */
    NG_PARSE_ERROR("4002"),
    /** Bad Request */
    NG_400_BAD_REQUEST("4400"),
    /** Not Found */
    NG_404_NOT_FOUND("4404"),
    /** Internal Server Error */
    NG_500_INTERNAL_SERVER_ERROR("4500"),
    /** Service Unavailable */
    NG_503_SERVICE_UNAVAILABLE("4503"),
    /** 内部エラー */
    NG_INTERNAL_ERROR("4700"),
    /** キャンペーンID不正 */
    NG_INVALID_CAMPAIGN_ID("4701"),
    /** メールアドレスとケーブルIDの組み合わせが異なる（既存メアドと既存ケーブルIDの組み合わせ） */
    NG_EMAIL_AND_CABLE_ID_MISMATCHED("4702"),
    /** メールアドレスとケーブルIDの組み合わせが異なる（新メアドと既存ケーブルIDの組み合わせ） */
    NG_ALREADY_USED_CABLE_ID("4703"),
    /** ケーブルID決済以外で契約済み */
    NG_ALREADY_SUBSCRIBED_OTHER("4704"),
    /** ケーブルID決済で契約済み */
    NG_ALREADY_SUBSCRIBED_CABLE_ID("4705"),
    /** メールアドレスが登録済み */
    NG_ALREADY_USED_EMAIL("4706"),
    /** メールアドレスとサービスコードの組み合わせが異なる */
    NG_EMAIL_AND_SERVICE_CODE_MISMATCHED("4707"),
    /** 解約済みのため、即時解約不可。解約済みのため、解約予約不可。解約済みのため、取消不可。 */
    NG_ALREADY_CANCELED("4901"),
    /** 解約予約済みのため、解約予約不可 */
    NG_ALREADY_PENDING("4902"),
    /** 決済依頼中のため、即時解約不可。決済依頼中のため、解約予約不可。 */
    NG_BILLING_HOLD("4903"),
    /** 即時解約ができないアカウントステータス。解約予約ができないアカウントステータス。解約取消ができないアカウントステータス。 */
    NG_INVALID_ACCOUNT_STATUS("4904"),
    /** 未知のケーブルIDを指定した場合 */
    NG_INVALID_CABLE_ID("4905"),
    /** ハンドリングできなかったエラー */
    UNKNOWN("9000");

    private String code;

    private ResultCode(String code) {
        this.code = code;
    }

    /** 正常終了で、.resultファイルに書込むコード.(エラー時は上記の4桁) */
    public static final String OK = "0";
    /**
     * codeを、大文字小文字を無視して、enumに変換.
     * 
     * @param code 結果コード
     * @return ResultCode
     */
    public static ResultCode valueFrom(String code) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getCode(), code))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }
    /**
     * .resultファイルに出力する結果コード.
     * 
     * @return
     */
    public String getResultCode() {
        if (SUCCESS == this) {
            return OK;
        } else {
            return this.getCode();
        }
    }
}
