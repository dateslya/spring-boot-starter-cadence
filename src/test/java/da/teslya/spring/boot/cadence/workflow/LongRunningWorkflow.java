package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface LongRunningWorkflow {
    @WorkflowMethod
    void run();
}
