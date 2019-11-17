package foo.sample.batch.exception;

import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

import foo.sample.constant.ChargeResult;
import foo.sample.constant.ResultCode;
import lombok.Getter;

/**
 * 業務Exception(エラー).
 */
@Getter
public class ServiceException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    // 業務エラーコード
    private ResultCode errorCode = ResultCode.UNKNOWN;
    // 日次課金連携エラーコード
    private ChargeResult chargeErrorCode = ChargeResult.UNKNOWN;

    public ServiceException() {
        super(ResultCode.UNKNOWN.name());
    }

    public ServiceException(ResultCode errorCode) {

        super(defaultIfNull(errorCode, ResultCode.UNKNOWN).name());
        this.errorCode = defaultIfNull(errorCode, ResultCode.UNKNOWN);
    }

    public ServiceException(ResultCode errorCode, Throwable cause) {

        super(defaultIfNull(errorCode, ResultCode.UNKNOWN).name(), cause);
        this.errorCode = defaultIfNull(errorCode, ResultCode.UNKNOWN);
    }

    public ServiceException(ChargeResult chargeErrorCode, Throwable cause) {

        // TODO name()は非推奨のため、toString()へ変更する（他の箇所とあわせて実施）
        super(defaultIfNull(chargeErrorCode, ChargeResult.UNKNOWN).name(), cause);
        this.chargeErrorCode = defaultIfNull(chargeErrorCode, ChargeResult.UNKNOWN);
    }

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(Throwable cause) {
        super(cause);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

}
