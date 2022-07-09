package da.teslya.spring.boot.cadence.domain;

import com.uber.cadence.RegisterDomainRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DefaultRegisterDomainRequestCustomizer implements
        RegisterDomainRequestCustomizer {

    @Override
    public void accept(RegisterDomainRequest request, DomainProperties props) {
        request
                .setName(props.getName())
                .setDescription(props.getDescription());
    }
}
