package da.teslya.spring.boot.cadence.workflow;

import da.teslya.spring.boot.cadence.activity.HelloActivities;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
@RequiredArgsConstructor
public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {
//    private final HelloActivities helloActivities;

    @Override
    public String hello(String name) {
//        return helloActivities.hello(StringUtils.hasText(name) ? name : "world");
        return String.format("Hello, %s!", name);
    }
}
