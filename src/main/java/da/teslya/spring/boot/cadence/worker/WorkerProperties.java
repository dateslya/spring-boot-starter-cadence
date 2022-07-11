package da.teslya.spring.boot.cadence.worker;

import da.teslya.spring.boot.cadence.activity.ActivityProperties;
import da.teslya.spring.boot.cadence.activity.local.LocalActivityProperties;
import da.teslya.spring.boot.cadence.worker.factory.WorkerFactoryProperties;
import da.teslya.spring.boot.cadence.workflow.WorkflowProperties;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class WorkerProperties {

    private String taskList = "default_task_list";
    private WorkerFactoryProperties factory = new WorkerFactoryProperties();
    Map<String, WorkflowProperties> workflows = new HashMap<>();
    Map<String, ActivityProperties> activities = new HashMap<>();
    Map<String, LocalActivityProperties> localActivities = new HashMap<>();
}
