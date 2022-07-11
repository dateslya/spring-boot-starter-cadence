package da.teslya.spring.boot.cadence.config;

import da.teslya.spring.boot.cadence.client.WorkflowClientProperties;
import da.teslya.spring.boot.cadence.domain.DomainProperties;
import da.teslya.spring.boot.cadence.service.WorkflowServiceProperties;
import da.teslya.spring.boot.cadence.worker.WorkerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("spring.cadence")
@Data
public class CadenceProperties {
    private WorkflowServiceProperties service = new WorkflowServiceProperties();
    private DomainProperties domain = new DomainProperties();
    private WorkflowClientProperties client = new WorkflowClientProperties();
    private WorkerProperties worker = new WorkerProperties();
}
