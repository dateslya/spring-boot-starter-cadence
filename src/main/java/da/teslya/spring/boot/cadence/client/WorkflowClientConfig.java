package da.teslya.spring.boot.cadence.client;

import com.uber.cadence.client.ActivityCompletionClient;
import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowClientOptions;
import com.uber.cadence.serviceclient.IWorkflowService;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import da.teslya.spring.boot.cadence.domain.DomainProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkflowClientConfig {

    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkflowClientOptionsBuilderCustomizer> builderCustomizers;
    private final IWorkflowService workflowService;

    @Bean
    public WorkflowClient workflowClient() {

        WorkflowClientProperties props = cadenceProperties.getClients()
                .getOrDefault("default", new WorkflowClientProperties());
        DomainProperties domainProps = cadenceProperties.getDomains().get(props.getDomain());

        WorkflowClientOptions.Builder builder = new WorkflowClientOptions.Builder();
        new Customizers<>(builderCustomizers).customize(builder, props);

        return WorkflowClient.newInstance(workflowService, domainProps.getName(), builder.build());
    }

    @Bean
    public ActivityCompletionClient activityCompletionClient(WorkflowClient workflowClient) {
        return workflowClient.newActivityCompletionClient();
    }
}
