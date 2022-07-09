package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.workflow.ChildWorkflowOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultChildWorkflowOptionsBuilderCustomizer implements
        ChildWorkflowOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, ChildWorkflowProperties props) {
        builder
                .setDomain(props.getDomain())
                .setTaskList(props.getTaskList());
    }
}
