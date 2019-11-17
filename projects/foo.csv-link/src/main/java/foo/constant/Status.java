package foo.sample.constant;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * 利用者ステータス.
 */
@Getter
public enum Status {
    /** 契約中 */
    NORMAL("0"),
    /** 利用停止中 */
    SUSPENDED("1"),
    /** 解約済み */
    TERMINATED("2"),
    /** validation error */
    UNKNOWN(null);

    private String code;

    private Status(String code) {
        this.code = code;
    }

    /**
     * codeを、大文字小文字を無視して、enumに変換.
     * 
     * @param code 利用者ステータス
     * @return Status
     */
    public static Status valueFrom(String code) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getCode(), code))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }
}
