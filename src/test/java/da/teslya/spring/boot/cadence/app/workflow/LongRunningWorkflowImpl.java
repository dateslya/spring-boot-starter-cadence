package da.teslya.spring.boot.cadence.app.workflow;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.common.RetryOptions;
import da.teslya.spring.boot.cadence.app.activity.LongRunningActivities;
import da.teslya.spring.boot.cadence.annotation.Workflow;

import java.time.Duration;

@Workflow
public class LongRunningWorkflowImpl implements LongRunningWorkflow {

    private final ActivityOptions options = new ActivityOptions.Builder()
            .setScheduleToStartTimeout(Duration.ofSeconds(1))
            .setStartToCloseTimeout(Duration.ofSeconds(2))
            .setScheduleToCloseTimeout(Duration.ofSeconds(5))
            .setHeartbeatTimeout(Duration.ofSeconds(10))
            .setRetryOptions(new RetryOptions.Builder()
                    .setInitialInterval(Duration.ofSeconds(1))
                    .setBackoffCoefficient(2.0d)
                    .setMaximumInterval(Duration.ofSeconds(30))
                    .setExpiration(Duration.ofSeconds(60))
                    .build())
            .build();
    private LongRunningActivities longRunningActivities
            = com.uber.cadence.workflow.Workflow.newActivityStub(LongRunningActivities.class, options);

    @Override
    public void run() {
        longRunningActivities.run();
    }
}
