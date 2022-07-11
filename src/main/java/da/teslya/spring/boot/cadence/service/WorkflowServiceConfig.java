package da.teslya.spring.boot.cadence.service;

import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.serviceclient.WorkflowServiceTChannel;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkflowServiceConfig {

    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkflowServiceTChannelClientOptionsBuilderCustomizer> builderCustomizers;

    @Bean
    public IWorkflowService workflowService() {

        WorkflowServiceProperties props = cadenceProperties.getService();

        WorkflowServiceTChannel.ClientOptions.Builder builder = new WorkflowServiceTChannel.ClientOptions.Builder();
        new Customizers<>(builderCustomizers).customize(builder, props);

        return new WorkflowServiceTChannel(props.getHost(), props.getPort(), builder.build());
    }
}
