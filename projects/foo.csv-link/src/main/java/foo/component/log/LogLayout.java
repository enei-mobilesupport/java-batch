package foo.sample.component.log;

import static org.apache.commons.lang3.StringUtils.defaultIfBlank;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.MDC;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.classic.spi.IThrowableProxy;
import ch.qos.logback.classic.spi.ThrowableProxyUtil;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.LayoutBase;
import foo.sample.component.LogComponent;
import foo.sample.constant.log.LogParam;

/**
 * ログレイアウト
 */
public class LogLayout extends LayoutBase<ILoggingEvent> {

    /** 日付フォーマット */
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("[yyyy/MM/dd] [HH:mm:ss.SSS]");

    /** ロガーのカウントダウン */
    private static final int LOGGER_CNT_DOWN = 7;

    /** nullの置き換え文字列 */
    private static final String NOTHING = "-----";

    public LogLayout() {
        super();
    }

    @Override
    public String doLayout(ILoggingEvent event) {
        // 日時
        String eventTime = DATE_FORMAT.format(new Date(event.getTimeStamp()));
        // ログレベル
        String level = event.getLevel().levelStr;
        if (event.getLevel() == Level.INFO || event.getLevel() == Level.WARN) {
            level = level + " ";
        }
        if (event.getLevel() == Level.ERROR && event.getMarker() != null) {
            level = event.getMarker().getName();
        }
        StackTraceElement callerStack = this.getLogCaller(event);
        // クラス
        String className = callerStack.getClassName();
        // メソッド
        String methodName = callerStack.getMethodName();
        String classMethod = className + "#" + methodName;

        // CATV事業者コード
        String catvCompanyCode = defaultIfBlank(MDC.get(LogParam.CATV_COMPANY_ID.name()), NOTHING);
        // SP ID
        String spid = defaultIfBlank(MDC.get(LogParam.SP_ID.name()), NOTHING);
        // 業務ID
        String serviceId = defaultIfBlank(MDC.get(LogParam.OPERATE_ID.name()), NOTHING);
        // 契約者ID
        String subscriberId = defaultIfBlank(MDC.get(LogParam.SUBSCRIBER_ID.name()), NOTHING);
        // メッセージ（ID, 本文）
        String message = event.getFormattedMessage();
        IThrowableProxy throwbleProxy = event.getThrowableProxy();

        StringBuffer sbuf = new StringBuffer();
        sbuf.append(eventTime);
        sbuf.append(" [");
        sbuf.append(level);
        sbuf.append("] [");
        sbuf.append(classMethod);
        sbuf.append("] [");
        sbuf.append(Thread.currentThread().getId());
        sbuf.append("] [");
        sbuf.append(catvCompanyCode);
        sbuf.append("] [");
        sbuf.append(serviceId);
        sbuf.append("] [");
        sbuf.append(spid);
        sbuf.append("] [");
        sbuf.append(subscriberId);
        sbuf.append("] ");
        sbuf.append(message);
        sbuf.append(CoreConstants.LINE_SEPARATOR);
        if (throwbleProxy != null) {
            String throwableStr = ThrowableProxyUtil.asString(throwbleProxy);
            sbuf.append(throwableStr);
            sbuf.append(CoreConstants.LINE_SEPARATOR);
        }
        return sbuf.toString();
    }

    /**
     * LogUtil経由で呼ばれていればLogUtilの呼出元を返す.<br>
     * 経由していなければLoggerの呼出元を返す.
     * 
     * @param event format対象
     * @return StackTrace
     */
    private StackTraceElement getLogCaller(ILoggingEvent event) {
        // LogUtilまでスタックを遡る
        StackTraceElement[] callerStack = Thread.currentThread().getStackTrace();
        boolean logUtilFound = false;
        boolean loggerFound = false;
        int i = 0;
        for (; i < callerStack.length; i++) {
            StackTraceElement stackTraceElement = callerStack[i];
            if (stackTraceElement.getClassName().equals(event.getLoggerName())) {
                loggerFound = true;
            } else if (loggerFound) {
                break;
            }
        }
        int loggerStackDepth = i;
        for (; i < loggerStackDepth + LOGGER_CNT_DOWN; i++) {
            StackTraceElement stackTraceElement = callerStack[i];
            if (stackTraceElement.getClassName().equals(LogComponent.class.getName())) {
                logUtilFound = true;
            }
            if (logUtilFound && !stackTraceElement.getClassName().equals(LogComponent.class.getName())) {
                return stackTraceElement;
            }
        }
        // LogUtil経由で呼ばれていないのでLogbackで通常期待される値を返す
        return callerStack[loggerStackDepth];
    }
}
