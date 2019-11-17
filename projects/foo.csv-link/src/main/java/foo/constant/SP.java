package foo.sample.constant;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * Service Provider.
 */
@Getter
public enum SP {
    /** dev(フールー)動画配信サービス */
    dev("dev"),
    /** DAZN(ダゾーン)動画配信サービス */
    DAZN("DAZN"),
    /** Paravi(パラビ)動画配信サービス */
    PARAVI("PARAVI"),
    /** validation error */
    UNKNOWN(null);

    private String id;

    private SP(String id) {
        this.id = id;
    }

    /**
     * idを、大文字小文字を無視して、enumに変換.
     * 
     * @param id SP_ID
     * @return SP
     */
    public static SP valueFrom(String id) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getId(), id))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }
}
