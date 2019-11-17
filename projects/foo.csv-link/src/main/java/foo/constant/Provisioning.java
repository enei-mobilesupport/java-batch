package foo.sample.constant;

import java.util.Arrays;

import org.springframework.util.StringUtils;

import lombok.Getter;

/**
 * サービスプロビジョニング.
 */
@Getter
public enum Provisioning {
    /** 契約申込 */
    CONTRACT("4", null, null, "契約申込"),
    /** 利用停止 */
    SUSPEND("3", "1", null, "利用停止"),
    /** 利用再開 */
    RESUME("3", "0", null, "利用再開"),
    /** 即時解約 */
    TERMINATE("5", null, true, "即時解約"),
    /** 解約予約 */
    TERMINATE_RESERVE("5", null, false, "解約予約"),
    /** 解約予約取消 */
    TERMINATE_RESERVE_CANCEL("Z", null, null, "解約予約取消"),
    /** validation error */
    UNKNOWN(null, null, null, "UNKNOWN");

    // みるプラスの操作区分（3:属性更新、4:鍵あけ、5:鍵閉め、Z：解約予約取消（dev独自））
    private String operateType;

    // 休止フラグ
    private String suspendFlag;

    // 指定期日があるか
    private Boolean hasDueDate;

    // 表記
    private String note;

    private Provisioning(String operateType, String suspendFlag, Boolean hasDueDate, String note) {
        this.operateType = operateType;
        this.suspendFlag = suspendFlag;
        this.hasDueDate = hasDueDate;
        this.note = note;
    }

    /**
     * csvファイルの入力項目に応じて、操作区分を返す.
     * 
     * @param operateType みるプラスの操作区分（3:属性更新、4:鍵あけ、5:鍵閉め、Z：解約予約取消（dev独自））
     * @param suspendFlag 休止フラグ
     * @param dueDate     指定期日
     * @return サービスプロビジョニングの操作区分
     */
    public static Provisioning valueOf(String operateType, String suspendFlag, String dueDate) {

        if ("4".equals(operateType)) {
            return CONTRACT;
        } else if ("3".equals(operateType) && "1".equals(suspendFlag)) {
            return SUSPEND;
        } else if ("3".equals(operateType) && "0".equals(suspendFlag)) {
            return RESUME;
        } else if ("5".equals(operateType) && StringUtils.hasText(dueDate)) {
            return TERMINATE;
        } else if ("5".equals(operateType) && !StringUtils.hasText(dueDate)) {
            return TERMINATE_RESERVE;
        } else if ("Z".equals(operateType)) {
            return TERMINATE_RESERVE_CANCEL;
        }
        return UNKNOWN;
    }

    /**
     * operateIdを、大文字小文字を無視して、enumに変換.
     * 
     * @param id operateId
     * @return Provisioning
     */
    public static Provisioning valueFrom(String id) {
        // @formatter:off
        return Arrays.stream(values())
                .filter(v -> v.name().equalsIgnoreCase(id))
                .findFirst()
                .orElse(UNKNOWN);
        // @formatter:on
    }

}
