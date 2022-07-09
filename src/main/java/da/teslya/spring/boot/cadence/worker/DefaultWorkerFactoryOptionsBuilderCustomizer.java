package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker.FactoryOptions.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultWorkerFactoryOptionsBuilderCustomizer implements
        WorkerFactoryOptionsBuilderCustomizer {

    @Override
    public void accept(Builder builder, WorkerFactoryProperties props) {

    }
}
