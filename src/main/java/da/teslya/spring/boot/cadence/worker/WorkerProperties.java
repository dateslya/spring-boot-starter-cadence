package da.teslya.spring.boot.cadence.worker;

import lombok.Data;

@Data
public class WorkerProperties {

    private String taskList = "default";
}
