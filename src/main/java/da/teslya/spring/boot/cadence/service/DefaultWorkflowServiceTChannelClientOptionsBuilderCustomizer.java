package da.teslya.spring.boot.cadence.service;

import com.uber.cadence.serviceclient.WorkflowServiceTChannel.ClientOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkflowServiceTChannelClientOptionsBuilderCustomizer implements
        WorkflowServiceTChannelClientOptionsBuilderCustomizer {
    @Override
    public void accept(Builder builder, WorkflowServiceProperties props) {

    }
}
