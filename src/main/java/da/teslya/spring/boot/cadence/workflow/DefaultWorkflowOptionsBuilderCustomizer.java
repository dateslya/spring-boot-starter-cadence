package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.client.WorkflowOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkflowOptionsBuilderCustomizer implements
        WorkflowOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, String name) {

    }
}
