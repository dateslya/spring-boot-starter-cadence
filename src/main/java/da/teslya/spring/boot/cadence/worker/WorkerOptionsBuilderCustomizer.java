package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.WorkerOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkerOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkerProperties> {

}
