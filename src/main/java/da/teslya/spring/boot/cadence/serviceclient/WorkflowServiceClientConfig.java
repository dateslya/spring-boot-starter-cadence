package da.teslya.spring.boot.cadence.serviceclient;

import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.CustomizersAcceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkflowServiceClientConfig {

    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkflowServiceClientOptionsBuilderCustomizer> builderCustomizers;

    @Bean
    public IWorkflowService workflowServiceClient() {

        WorkflowServiceClientProperties props = cadenceProperties.getService();

        WorkflowServiceTChannel.ClientOptions.Builder builder = new WorkflowServiceTChannel.ClientOptions.Builder();
        new CustomizersAcceptor<>(builderCustomizers).accept(builder, props);

        return new WorkflowServiceTChannel(props.getHost(), props.getPort(), builder.build());
    }
}
