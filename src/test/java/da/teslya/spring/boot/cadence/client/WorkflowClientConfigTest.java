package da.teslya.spring.boot.cadence.client;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import da.teslya.spring.boot.cadence.config.CadenceAutoConfiguration;
import da.teslya.spring.boot.cadence.workflow.HelloWorldWorkflow;
import da.teslya.spring.boot.cadence.workflow.PingWorkflow;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = CadenceAutoConfiguration.class)
class WorkflowClientConfigTest {

    @Autowired
    private WorkflowClient workflowClient;

    @Test
    void checkDefaultConfiguration() {

        WorkflowOptions options = new WorkflowOptions.Builder()
                .setTaskList("my_task_list")
                .setExecutionStartToCloseTimeout(Duration.ofSeconds(10))
                .build();

        assertEquals("my_domain_name", workflowClient.getDomain());
        assertEquals("Hello, Dmitry!", workflowClient.newWorkflowStub(HelloWorldWorkflow.class, options).hello("Dmitry"));
        assertEquals("Pong", workflowClient.newWorkflowStub(PingWorkflow.class, options).ping("Ping"));
    }
}