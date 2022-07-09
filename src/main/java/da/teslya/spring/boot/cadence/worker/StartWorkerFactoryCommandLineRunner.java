package da.teslya.spring.boot.cadence.worker;

import com.uber.cadence.worker.Worker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(20)
@Component
@RequiredArgsConstructor
public class StartWorkerFactoryCommandLineRunner implements CommandLineRunner {

    private final ObjectProvider<Worker.Factory> workerFactories;

    @Override
    public void run(String... args) throws Exception {
        workerFactories.forEach(factory -> {
            factory.start();
            log.info("Worker Factory successfully started");
        });
    }
}
