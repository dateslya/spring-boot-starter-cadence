package da.teslya.spring.boot.cadence.app.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface PingWorkflow {
    @WorkflowMethod
    String ping(String ping);
}
