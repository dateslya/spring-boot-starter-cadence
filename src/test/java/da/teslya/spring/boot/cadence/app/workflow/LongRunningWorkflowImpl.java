package da.teslya.spring.boot.cadence.app.workflow;

import da.teslya.spring.boot.cadence.annotation.Workflow;
import da.teslya.spring.boot.cadence.app.activity.LongRunningActivities;
import lombok.RequiredArgsConstructor;

@Workflow
@RequiredArgsConstructor
public class LongRunningWorkflowImpl implements LongRunningWorkflow {

    private final LongRunningActivities longRunningActivities;

    @Override
    public void run() {
        longRunningActivities.run();
    }
}
