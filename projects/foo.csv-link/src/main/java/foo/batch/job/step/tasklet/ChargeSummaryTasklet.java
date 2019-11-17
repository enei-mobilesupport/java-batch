package foo.sample.batch.job.step.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.db.auto.crud.ChargeMapper;

/**
 * 事業者間精算レポート出力. CATV局ごとの課金数を集計する.
 */
public class ChargeSummaryTasklet implements Tasklet, InitializingBean {

    // TODO 事業者間精算レポート出力の詳細は未定 on 12/21.
    private String directory;

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        // フォルダの存在チェックなど、実行前処理.
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

        // 現在日付から、１ヶ月の期間の設定.
        // 課金テーブルに対し、Select文を投げる.
        // 出力ファイル名(パス)の生成.
        // 結果を CSVファイル出力する. または、Backlogの任意のプロジェクトへメール送信による課題登録

        return RepeatStatus.FINISHED;
    }

}
