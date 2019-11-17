package foo.sample.component;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Locale;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;

import org.apache.commons.lang3.StringUtils;
import org.jboss.logging.MDC;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.HierarchicalMessageSource;
import org.springframework.context.MessageSource;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequest;
import org.springframework.stereotype.Component;

import foo.sample.constant.Provisioning;
import foo.sample.constant.log.LogLevel;
import foo.sample.constant.log.LogParam;

/**
 * ログ出力.
 */
@Component
public class LogComponent {

    private static final Logger LOG = LoggerFactory.getLogger(LogComponent.class);

    private static final Marker FATAL = MarkerFactory.getMarker(LogLevel.FATAL.getLevel());
    private static final Marker ALERT = MarkerFactory.getMarker(LogLevel.ALERT.getLevel());

    /** ログレベルが格納されているインデックス **/
    private static final int LOGLEVEL_INDEX = 1;
    /** ログレベルを取得するためのデリミタ **/
    private static final String MESSAGE_DELIMITER = ".";

    @Autowired
    MessageSource messageSource;

    @PostConstruct
    public void setMessageSource() {
        if (!(this.messageSource instanceof DelegatingMessageSource)) {
            return;
        }
        ResourceBundleMessageSource msgSrc = new ResourceBundleMessageSource();
        msgSrc.setBasename("messages");
        msgSrc.setDefaultEncoding(UTF_8.name());
        HierarchicalMessageSource src = (HierarchicalMessageSource) this.messageSource;
        src.setParentMessageSource(msgSrc);
    }

    /**
     * メッセージを取得・作成する.
     * 
     * @param key    メッセージのキー
     * @param params メッセージパラメータ
     * @return メッセージ文字列
     */
    private String getMessage(String key, Object[] params) {
        String messageStr = messageSource.getMessage(key, params, Locale.JAPAN);
        return "[" + key + "] " + messageStr;
    }

    /**
     * アクションログの出力項目をMDCにまとめて設定する.
     * 
     * @param catvCode     CATV事業者コード
     * @param spId         SP_ID
     * @param operateId    業務ID
     * @param subscriberId 契約者ID
     */
    public void setTarget(String catvCode, String spId, String operateId, String subscriberId) {
        MDC.put(LogParam.CATV_COMPANY_ID.name(), catvCode);
        MDC.put(LogParam.SP_ID.name(), spId);
        MDC.put(LogParam.OPERATE_ID.name(), operateId);
        MDC.put(LogParam.SUBSCRIBER_ID.name(), subscriberId);
    }

    /**
     * ログ出力の為に、サービスプロビジョニングを返す.
     * 
     * @return Provisioning
     */
    public Provisioning getProvisioning() {
        String operateId = (String) MDC.get(LogParam.OPERATE_ID.name());

        return Provisioning.valueFrom(operateId);
    }

    /**
     * メッセージIDをデリミタで分割してログレベルを判定し、<br>
     * ログメッセージを作成して出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    public void log(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);

        String[] message = messageId.split(Pattern.quote(MESSAGE_DELIMITER));
        LogLevel logLevel = LogLevel.valueFrom(message[LOGLEVEL_INDEX]);

        switch (logLevel) {
        case DEBUG:
            LOG.debug(msg, params);
            break;
        case INFO:
            LOG.info(msg, params);
            break;
        case WARN:
            LOG.warn(msg, params);
            break;
        case ERROR:
            LOG.error(msg, params);
            break;
        case FATAL:
            LOG.error(FATAL, msg, params);
            break;
        case ALERT:
            LOG.error(ALERT, msg, params);
            break;
        default:
            // TODO ログレベルが想定外の場合の通知方法を検討する
            throw new IllegalArgumentException("Log Level cannot be found from message id.");
        }
    }

    /**
     * メッセージIDをデリミタで分割してログレベルを判定し、<br>
     * ログメッセージを作成して出力する。
     * 
     * @param messageId メッセージID
     * @param e         例外
     * @param params    パラメタ
     */
    public void log(String messageId, Throwable e, Object... params) {
        String msg = this.getMessage(messageId, params);

        String[] message = messageId.split(Pattern.quote(MESSAGE_DELIMITER));
        LogLevel logLevel = LogLevel.valueFrom(message[LOGLEVEL_INDEX]);

        switch (logLevel) {
        case DEBUG:
            LOG.debug(msg, e);
            break;
        case INFO:
            LOG.info(msg, e);
            break;
        case WARN:
            LOG.warn(msg, e);
            break;
        case ERROR:
            LOG.error(msg, e);
            break;
        case FATAL:
            LOG.error(FATAL, msg, e);
            break;
        case ALERT:
            LOG.error(ALERT, msg, e);
            break;
        default:
            // TODO ログレベルが想定外の場合の通知方法を検討する
            throw new IllegalArgumentException("Log Level cannot be found from message id.");
        }
    }

    /**
     * Debugレベルのログを出力する.<br>
     * 開発用にメッセージIDなしでのログ出力を許容する.
     * 
     * @param msg メッセージ
     */
    public void debug(String msg) {
        LOG.debug(msg);
    }

    /**
     * Debugレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void debug(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.debug(msg, params);
    }

    /**
     * Infoレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void info(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.info(msg, params);
    }

    /**
     * Infoレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param e         例外
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void info(String messageId, Throwable e, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.info(msg, e);
    }

    /**
     * Warnレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void warn(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.warn(msg, params);
    }

    /**
     * Warnレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param e         例外
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void warn(String messageId, Throwable e, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.warn(msg, e);
    }

    /**
     * Errorレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void error(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.error(msg, params);
    }

    /**
     * Errorレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param e         例外
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void error(String messageId, Throwable e, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.error(msg, e);
    }

    /**
     * Fatalレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void fatal(String messageId, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.error(FATAL, msg);
    }

    /**
     * Fatalレベルのログを出力する.
     * 
     * @param messageId メッセージID
     * @param e         例外
     * @param params    メッセージパラメータ
     */
    @Deprecated
    public void fatal(String messageId, Throwable e, Object... params) {
        String msg = this.getMessage(messageId, params);
        LOG.error(FATAL, msg, e);
    }

    /**
     * ""API Request: URI={}, Headers={}, Body={}""の形式でログ出力.
     * 
     * @param entity APIコールのリクエスト
     * @throws IOException
     */
    public void log(ClientHttpRequest request) throws IOException {
        if (request == null) {
            log("SIM.ERROR.9992", "error. no request.");
            return;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.writeTo(request.getBody());

        byte[] body = byteArrayOutputStream.toByteArray();
        String json = StringUtils.defaultIfBlank(new String(body, UTF_8), "{see other log part.}");

        // @formatter:off
        log("SIM.INFO.0101",
                request.getURI(),
                request.getHeaders(),
                json);
        // @formatter:on
    }

    /**
     * "API Response: StatusCode={} {}, Headers={}, Body={}"の形式でログ出力.
     * 
     * @param entity APIコールのレスポンス
     */
    public void log(ResponseEntity<?> entity) {
        if (entity == null) {
            log("SIM.ERROR.9992", "error. no response.");
            return;
        }
        HttpStatus statusCode = entity.getStatusCode();
        // @formatter:off
        log("SIM.INFO.0102",
                statusCode.value(),
                statusCode.name(),
                entity.getHeaders(),
                entity.getBody());
        // @formatter:on
    }

}
