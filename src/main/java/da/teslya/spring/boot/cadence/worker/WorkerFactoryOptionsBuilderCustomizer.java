package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker.FactoryOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkerFactoryOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkerFactoryProperties> {

}
