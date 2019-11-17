package foo.sample.batch.job.step.writer;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import foo.sample.component.LogComponent;

public class ConsoleItemWriter<T> implements ItemWriter<T> {

    @Autowired
    private LogComponent logger;

    @Override
    public void write(List<? extends T> items) throws Exception {
        for (T item : items) {
            // TODO 開発の終盤で、System.out.println()を、ログ出力に置換える.
            if (item == null) {
                logger.debug("null");
            } else {
                logger.debug(item.toString());
            }
        }
    }
}
