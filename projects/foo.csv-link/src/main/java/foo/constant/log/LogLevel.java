package foo.sample.constant.log;

import java.util.Arrays;

import lombok.Getter;

/**
 * Logレベル.
 */
@Getter
public enum LogLevel {
    // @formatter:off
    DEBUG("DEBUG"),
    INFO("INFO"),
    WARN("WARN"),
    ERROR("ERROR"),
    FATAL("FATAL"),
    ALERT("ALERT");
    // @formatter:on
    private String level;

    /**
     * 個別のLogレベル作成.
     * 
     * @param level 識別字
     */
    private LogLevel(String level) {
        this.level = level;
    }

    /**
     * 頭文字からLogレベルを取得する
     * 
     * @param level 識別字
     * @return Logレベル
     */
    public static LogLevel valueFrom(String level) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(bl -> bl.level.equalsIgnoreCase(level))
                .findFirst()
                .orElse(null);
        // @formatter:on
    }

}
