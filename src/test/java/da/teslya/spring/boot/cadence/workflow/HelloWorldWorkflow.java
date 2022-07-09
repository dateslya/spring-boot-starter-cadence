package da.teslya.spring.boot.cadence.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface HelloWorldWorkflow {

    @WorkflowMethod
    String hello(String name);
}
