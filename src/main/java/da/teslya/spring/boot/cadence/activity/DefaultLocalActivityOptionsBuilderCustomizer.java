package da.teslya.spring.boot.cadence.activity;

import com.uber.cadence.activity.LocalActivityOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultLocalActivityOptionsBuilderCustomizer implements
        LocalActivityOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, LocalActivityProperties props) {
        builder
                .setScheduleToCloseTimeout(props.getScheduleToCloseTimeout());
    }
}
