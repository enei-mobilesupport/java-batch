package foo.sample.constant;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * 月額課金フラグ or 継続課金フラグ.
 */
@Getter
public enum ChargeType {
    /** 都度課金 */
    EACH("0"),
    /** 月額継続課金 */
    MONTHLY("1"),
    /** 特定期間継続課金 */
    SPECIFIC("2"),
    /** validation error */
    UNKNOWN(null);

    private String code;

    private ChargeType(String code) {
        this.code = code;
    }

    /**
     * codeを、大文字小文字を無視して、enumに変換.
     * 
     * @param code 月額(継続)課金フラグ
     * @return ChargeType
     */
    public static ChargeType valueFrom(String code) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getCode(), code))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }
}
