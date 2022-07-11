package da.teslya.spring.boot.cadence.workflow;

import java.time.Duration;

@Workflow
public class LongRunningWorkflowImpl implements LongRunningWorkflow {
    @Override
    public void run() {
        com.uber.cadence.workflow.Workflow.sleep(Duration.ofSeconds(2));
    }
}
