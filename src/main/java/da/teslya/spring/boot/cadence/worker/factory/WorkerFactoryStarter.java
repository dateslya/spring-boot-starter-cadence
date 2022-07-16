package da.teslya.spring.boot.cadence.worker.factory;

import com.uber.cadence.worker.Worker;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Slf4j
@Order(20)
@Component
@RequiredArgsConstructor
public class WorkerFactoryStarter implements CommandLineRunner {

    private final Worker.Factory workerFactory;

    @Override
    public void run(String... args) {
        workerFactory.start();
        log.info("Worker Factory successfully started");
    }
}
