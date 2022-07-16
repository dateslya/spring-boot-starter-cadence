package da.teslya.spring.boot.cadence.config;

import da.teslya.spring.boot.cadence.client.WorkflowClientProperties;
import da.teslya.spring.boot.cadence.domain.DomainProperties;
import da.teslya.spring.boot.cadence.serviceclient.WorkflowServiceClientProperties;
import da.teslya.spring.boot.cadence.worker.WorkerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties("spring.cadence")
public class CadenceProperties {
    private WorkflowServiceClientProperties service = new WorkflowServiceClientProperties();
    private DomainProperties domain = new DomainProperties();
    private WorkflowClientProperties client = new WorkflowClientProperties();
    private WorkerProperties worker = new WorkerProperties();
}
