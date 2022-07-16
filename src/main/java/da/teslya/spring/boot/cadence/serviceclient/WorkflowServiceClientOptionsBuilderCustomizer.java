package da.teslya.spring.boot.cadence.serviceclient;

import com.uber.cadence.serviceclient.WorkflowServiceTChannel.ClientOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkflowServiceClientOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkflowServiceClientProperties> {

}
