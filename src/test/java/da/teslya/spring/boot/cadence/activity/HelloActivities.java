package da.teslya.spring.boot.cadence.activity;

import com.uber.cadence.activity.ActivityMethod;

public interface HelloActivities {
    @ActivityMethod
    String hello(String name);
}
