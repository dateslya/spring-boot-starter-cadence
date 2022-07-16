package da.teslya.spring.boot.cadence.app.workflow;

import com.uber.cadence.workflow.WorkflowMethod;

public interface HelloWorldWorkflow {

    @WorkflowMethod
    String hello(String name);
}
