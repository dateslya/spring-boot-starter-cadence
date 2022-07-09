package da.teslya.spring.boot.cadence.service;

import lombok.Data;

@Data
public class WorkflowServiceProperties {

    private String host = "localhost";
    private int port = 7933;
}
