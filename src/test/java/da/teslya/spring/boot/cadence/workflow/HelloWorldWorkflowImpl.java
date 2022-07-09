package da.teslya.spring.boot.cadence.workflow;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Scope(BeanDefinition.SCOPE_PROTOTYPE)
@Component
public class HelloWorldWorkflowImpl implements HelloWorldWorkflow {
    @Override
    public String hello(String name) {
        return String.format("Hello, %s!", name);
    }
}
