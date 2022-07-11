package da.teslya.spring.boot.cadence.activity;

import org.springframework.stereotype.Component;

@Component
public class HelloActivitiesImpl implements HelloActivities {
    @Override
    public String hello(String name) {
        return String.format("Hello, %s!", name);
    }
}
