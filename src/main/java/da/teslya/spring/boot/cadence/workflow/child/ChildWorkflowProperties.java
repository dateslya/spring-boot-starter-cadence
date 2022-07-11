package da.teslya.spring.boot.cadence.workflow.child;

import lombok.Data;

@Data
public class ChildWorkflowProperties {

    private String domain;
    private String taskList;
}
