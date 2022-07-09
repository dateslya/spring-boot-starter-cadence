package da.teslya.spring.boot.cadence.client;

import com.uber.cadence.client.WorkflowClientOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkflowClientOptionsBuilderCustomizer implements
        WorkflowClientOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, WorkflowClientProperties workflowClientProperties) {

    }
}
