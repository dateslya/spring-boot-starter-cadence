package da.teslya.spring.boot.cadence.config;

import da.teslya.spring.boot.cadence.client.WorkflowClientProperties;
import da.teslya.spring.boot.cadence.domain.DomainProperties;
import da.teslya.spring.boot.cadence.service.WorkflowServiceProperties;
import da.teslya.spring.boot.cadence.worker.TaskListProperties;
import da.teslya.spring.boot.cadence.worker.WorkerFactoryProperties;
import da.teslya.spring.boot.cadence.worker.WorkerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@ConfigurationProperties("spring.cadence")
@Data
public class CadenceProperties {
    private Map<String, WorkflowServiceProperties> services = new HashMap<>();
    private Map<String, DomainProperties> domains = new HashMap<>();
    private Map<String, WorkflowClientProperties> clients = new HashMap<>();
    private Map<String, WorkerFactoryProperties> workerFactories = new HashMap<>();
    private Map<String, WorkerProperties> workers = new HashMap<>();
    private Map<String, TaskListProperties> taskLists = new HashMap<>();
}
