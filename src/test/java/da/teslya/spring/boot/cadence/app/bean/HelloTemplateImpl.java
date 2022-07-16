package da.teslya.spring.boot.cadence.app.bean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloTemplateImpl implements HelloTemplate {

    @Value("${template.hello}")
    private String template;

    @Override
    public String getMessage(String name) {
        return String.format(template, name);
    }
}
