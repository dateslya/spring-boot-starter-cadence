package da.teslya.spring.boot.cadence.client;

import com.uber.cadence.client.WorkflowClientOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkflowClientOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkflowClientProperties> {

}
