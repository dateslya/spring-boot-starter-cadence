package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.serviceclient.IWorkflowService;
import com.uber.cadence.worker.Worker;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import da.teslya.spring.boot.cadence.domain.DomainProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WorkerFactoryConfig {

    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<WorkerFactoryOptionsBuilderCustomizer> builderCustomizers;
    private final IWorkflowService workflowService;

    @Bean
    public Worker.Factory workerFactory() {

        WorkerFactoryProperties props = cadenceProperties.getWorkerFactories()
                .getOrDefault("default", new WorkerFactoryProperties());
        DomainProperties domainProps = cadenceProperties.getDomains().get(props.getDomain());

        Worker.FactoryOptions.Builder builder = new Worker.FactoryOptions.Builder();
        new Customizers<>(builderCustomizers).customize(builder, props);

        return new Worker.Factory(workflowService, domainProps.getName(), builder.build());
    }
}
