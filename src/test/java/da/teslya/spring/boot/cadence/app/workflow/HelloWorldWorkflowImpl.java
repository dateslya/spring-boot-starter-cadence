package da.teslya.spring.boot.cadence.app.workflow;

import da.teslya.spring.boot.cadence.app.activity.HelloActivities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor
public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {

    private final HelloActivities helloActivities;

    @Override
    public String hello(String name) {
        return helloActivities.hello(StringUtils.hasText(name) ? name : "world");
    }
}
