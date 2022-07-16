package da.teslya.spring.boot.cadence.client;

import com.uber.cadence.client.WorkflowClient;
import com.uber.cadence.client.WorkflowOptions;
import com.uber.cadence.client.WorkflowTimedOutException;
import da.teslya.spring.boot.cadence.config.CadenceAutoConfiguration;
import da.teslya.spring.boot.cadence.app.workflow.HelloWorldWorkflow;
import da.teslya.spring.boot.cadence.app.workflow.LongRunningWorkflow;
import da.teslya.spring.boot.cadence.app.workflow.PingWorkflow;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest(classes = {CadenceAutoConfiguration.class})
class WorkflowClientConfigTest {

    @Value("${spring.cadence.domain.name}")
    private String domainName;
    @Value("${spring.cadence.worker.task-list}")
    private String taskList;
    @Autowired
    private WorkflowClient workflowClient;

    @Autowired
    private ApplicationContext applicationContext;

    private WorkflowOptions options;

    @BeforeEach
    void initOptions() {
        options = new WorkflowOptions.Builder()
                .setTaskList(taskList)
                .setExecutionStartToCloseTimeout(Duration.ofSeconds(5))
                .build();
    }

    @Test
    void defaultConfiguration() {
        assertEquals(domainName, workflowClient.getDomain());
    }

    @Test
    void registerPrototype() {
        assertEquals("Hello, Dmitry!", workflowClient.newWorkflowStub(HelloWorldWorkflow.class, options).hello("Dmitry"));
    }

    @Test
    void registerWorkflow() {
        assertEquals("Pong", workflowClient.newWorkflowStub(PingWorkflow.class, options).ping("Ping"));
    }

    @Test
    void callLongRunningWorkflow() {

        WorkflowTimedOutException exception = assertThrows(
                WorkflowTimedOutException.class,
                () -> workflowClient.newWorkflowStub(LongRunningWorkflow.class, options).run());
        assertNotNull(exception);
    }
}