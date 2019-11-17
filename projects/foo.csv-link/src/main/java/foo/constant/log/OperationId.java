package foo.sample.constant.log;

import lombok.Getter;

/**
 * 日次課金連携 or 月次課金連携.
 */
@Getter
public enum OperationId {
    /** 日次課金連携 */
    DAILY("DAILY"),
    /** 月次課金連携 */
    MONTHLY("MONTHLY"),
    /** validation error */
    UNKNOWN(null);

    private String code;

    private OperationId(String code) {
        this.code = code;
    }
}
