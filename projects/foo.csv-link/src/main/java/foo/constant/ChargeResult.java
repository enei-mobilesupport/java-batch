package foo.sample.constant;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * CHARGEテーブルに登録する結果コード.<br>
 * 4桁コードはエラー詳細をログ出力するために定義.
 * <ul>
 * <li>0：請求可</li>
 * <li>１：請求不可</li>
 * <li>9：未知のケーブルIDまたはエラー</li>
 * </ul>
 */
@Getter
public enum ChargeResult {
    /** 正常終了 */
    SUCCESS("0000"),
    /** 請求不可 */
    NOT_CHARGE("6000"),
    /** 未知のケーブルIDを指定した場合 */
    NO_SUCH_CABLE_ID("6001"),
    /** 日付項目のフォーマットが想定外の場合 */
    DATE_FORMAT_ERROR("6002"),
    /** ハンドリングできなかったエラー */
    UNKNOWN("9000");

    private String code;

    private ChargeResult(String code) {
        this.code = code;
    }

    /** 請求可 */
    public static final String OK = "0";
    /** 請求不可 */
    public static final String CHARGE_NG = "1";
    /** 存在しないケーブルIDまたは日次連携でエラー */
    public static final String CHARGE_ERROR = "9";

    /**
     * codeを、大文字小文字を無視して、enumに変換.
     * 
     * @param code 結果コード
     * @return ResultCode
     */
    public static ChargeResult valueFrom(String code) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getChargeResultCode(), code))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }

    /**
     * 日次課金の.resultファイルに出力する結果コード.
     * 
     * @return ファイル出力コード
     */
    public String getChargeResultCode() {
        if (SUCCESS == this) {
            return OK;
        } else if (NOT_CHARGE == this) {
            return CHARGE_NG;
        } else if (NO_SUCH_CABLE_ID == this) {
            return CHARGE_ERROR;
        } else if (DATE_FORMAT_ERROR == this) {
            return CHARGE_ERROR;
        } else {
            return CHARGE_ERROR;
        }
    }
}
