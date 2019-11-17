package foo.sample.constant.control;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import lombok.Getter;

/**
 * Stepの終了コード.
 */
@Getter
public enum StepExitCode {
    /** ファイルが存在しない */
    NOT_EXIST("NOT_EXIST"),
    /** ファイル移動に失敗 */
    MOVE_FAILED("MOVE_FAILED"),
    /** validation error */
    UNKNOWN(null);

    private String code;

    private StepExitCode(String code) {
        this.code = code;
    }

    /**
     * codeを、大文字小文字を無視して、enumに変換.
     * 
     * @param code ファイル操作エラー
     * @return FileExitStatus
     */
    public static StepExitCode valueFrom(String code) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> StringUtils.equalsIgnoreCase(v.getCode(), code))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }

}
