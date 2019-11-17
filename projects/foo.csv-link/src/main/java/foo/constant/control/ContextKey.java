package foo.sample.constant.control;

import lombok.Getter;

/**
 * Step間でデータの受け渡しを行う際のキー名.
 */
@Getter
public enum ContextKey {
    /** SMSから連携されたCSVファイルパス */
    SMS_CSV_FILEPATH("sms_file_path"),
    /** ワークディレクトリへ移動後のCSVファイルパス */
    SMS_WORK_CSV_FILEPATH("sms_work_file_path");

    private String key;

    private ContextKey(String key) {
        this.key = key;
    }
}
