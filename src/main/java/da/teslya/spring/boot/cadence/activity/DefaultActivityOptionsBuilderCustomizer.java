package da.teslya.spring.boot.cadence.activity;

import com.uber.cadence.activity.ActivityOptions;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultActivityOptionsBuilderCustomizer implements
        ActivityOptionsBuilderCustomizer {

    @Override
    public void accept(ActivityOptions.Builder builder, ActivityProperties props) {
        builder
                .setTaskList(props.getTaskList())
                .setScheduleToStartTimeout(props.getScheduleToStartTimeout())
                .setStartToCloseTimeout(props.getStartToCloseTimeout())
                .setScheduleToCloseTimeout(props.getScheduleToCloseTimeout())
                .setHeartbeatTimeout(props.getHeartbeatTimeout());
    }
}
