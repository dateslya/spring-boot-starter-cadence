package da.teslya.spring.boot.cadence.activity;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ActivityHolder {

    private static final ActivityHolder INSTANCE = new ActivityHolder();

    private final Map<String, Class<?>> activities = new ConcurrentHashMap<>(64);

    private ActivityHolder() {
    }

    public static ActivityHolder getInstance() {
        return INSTANCE;
    }

    public void put(String beanName, Class<?> interfaceType) {
        activities.put(beanName, interfaceType);
    }

    public Map<String, Class<?>> getAll() {
        return Collections.unmodifiableMap(activities);
    }
}
