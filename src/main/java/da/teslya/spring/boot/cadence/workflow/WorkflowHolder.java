package da.teslya.spring.boot.cadence.workflow;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class WorkflowHolder {

    private static final WorkflowHolder INSTANCE = new WorkflowHolder();

    private final Map<String, Class<?>> workflows = new ConcurrentHashMap<>(16);

    private WorkflowHolder() {
    }

    public static WorkflowHolder getInstance() {
        return INSTANCE;
    }

    public void put(String beanName, Class<?> interfaceType) {
        workflows.put(beanName, interfaceType);
    }

    public Map<String, Class<?>> getAll() {
        return Collections.unmodifiableMap(workflows);
    }
}
