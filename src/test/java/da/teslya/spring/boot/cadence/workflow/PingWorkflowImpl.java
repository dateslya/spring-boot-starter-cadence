package da.teslya.spring.boot.cadence.workflow;

@Workflow
public class PingWorkflowImpl implements PingWorkflow {
    @Override
    public String ping(String ping) {
        return "Pong";
    }
}
