package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.client.WorkflowOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkflowOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkflowProperties> {

}
