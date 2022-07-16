package da.teslya.spring.boot.cadence.serviceclient;

import com.uber.cadence.serviceclient.WorkflowServiceTChannel.ClientOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkflowServiceClientOptionsBuilderCustomizer implements
        WorkflowServiceClientOptionsBuilderCustomizer {
    @Override
    public void accept(Builder builder, WorkflowServiceClientProperties props) {

    }
}
