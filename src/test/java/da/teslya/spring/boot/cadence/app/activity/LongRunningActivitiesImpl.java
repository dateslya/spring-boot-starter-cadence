package da.teslya.spring.boot.cadence.app.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class LongRunningActivitiesImpl implements LongRunningActivities {

    private int count = 0;

    @Override
    public void run() {
        if (count == 3) {
            return;
        }
        count++;
        throw new RuntimeException("Unexpected error");
//        try {
//            log.info("{} run()", count++);
//            TimeUnit.SECONDS.sleep(180);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}
