package da.teslya.spring.boot.cadence.app.workflow;

import com.uber.cadence.activity.ActivityOptions;
import com.uber.cadence.workflow.Workflow;
import da.teslya.spring.boot.cadence.app.activity.HelloActivities;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.Duration;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    private final ActivityOptions options = new ActivityOptions.Builder()
            .setScheduleToCloseTimeout(Duration.ofSeconds(5))
            .build();
    private final HelloActivities helloActivities = Workflow.newActivityStub(HelloActivities.class, options);

    @Override
    public String hello(String name) {
        return helloActivities.hello(StringUtils.hasText(name) ? name : "world");
    }
}
