package da.teslya.spring.boot.cadence.config;

import lombok.Data;

import java.time.Duration;
import java.util.List;

@Data
public class RetryProperties {

    private Duration initialInterval;
    private Duration expiration;
    private double backoffCoefficient;
    private int maximumAttempts;
    private Duration maximumInterval;
    private List<Class<? extends Throwable>> doNotRetry;
}
