package da.teslya.spring.boot.cadence.activity.local;

import da.teslya.spring.boot.cadence.config.RetryProperties;
import lombok.Data;

import java.time.Duration;

@Data
public class LocalActivityProperties {

    private Duration scheduleToCloseTimeout;
    private RetryProperties retry;
}
