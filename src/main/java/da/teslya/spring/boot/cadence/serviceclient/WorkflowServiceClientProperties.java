package da.teslya.spring.boot.cadence.serviceclient;

import lombok.Data;

@Data
public class WorkflowServiceClientProperties {

    private String host = "localhost";
    private int port = 7933;
}
