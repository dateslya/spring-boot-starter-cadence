package da.teslya.spring.boot.cadence.service;

import com.uber.cadence.serviceclient.WorkflowServiceTChannel.ClientOptions.Builder;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface WorkflowServiceTChannelClientOptionsBuilderCustomizer extends
        BiConsumer<Builder, WorkflowServiceProperties> {

}
