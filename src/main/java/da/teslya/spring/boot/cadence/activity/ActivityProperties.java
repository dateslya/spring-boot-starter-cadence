package da.teslya.spring.boot.cadence.activity;

import da.teslya.spring.boot.cadence.config.RetryProperties;
import lombok.Data;

import java.time.Duration;

@Data
public class ActivityProperties {

    private String taskList;
    private Duration scheduleToCloseTimeout;
    private Duration scheduleToStartTimeout;
    private Duration startToCloseTimeout;
    private Duration heartbeatTimeout;
    private RetryProperties retry;
}
