package da.teslya.spring.boot.cadence.app.workflow;

import da.teslya.spring.boot.cadence.annotation.Workflow;

@Workflow
public class PingWorkflowImpl implements PingWorkflow {
    @Override
    public String ping(String ping) {
        return "Pong";
    }
}
