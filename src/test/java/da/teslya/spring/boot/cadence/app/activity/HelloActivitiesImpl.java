package da.teslya.spring.boot.cadence.app.activity;

import da.teslya.spring.boot.cadence.app.bean.HelloTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HelloActivitiesImpl implements HelloActivities {
    private final HelloTemplate helloTemplate;

    @Override
    public String hello(String name) {
        return helloTemplate.getMessage(name);
    }
}
