package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.WorkerOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkerOptionsBuilderCustomizer implements
        WorkerOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, WorkerProperties props) {

    }
}
