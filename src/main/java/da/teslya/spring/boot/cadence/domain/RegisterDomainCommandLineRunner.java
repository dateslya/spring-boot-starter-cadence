package da.teslya.spring.boot.cadence.domain;

import com.uber.cadence.DomainAlreadyExistsError;
import com.uber.cadence.RegisterDomainRequest;
import com.uber.cadence.serviceclient.IWorkflowService;
import da.teslya.spring.boot.cadence.config.CadenceProperties;
import da.teslya.spring.boot.cadence.config.Customizers;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Order(0)
@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterDomainCommandLineRunner implements CommandLineRunner {

    private final CadenceProperties cadenceProperties;
    private final ObjectProvider<RegisterDomainRequestCustomizer> builderCustomizers;
    private final IWorkflowService workflowService;

    @Override
    public void run(String... args) throws Exception {

        DomainProperties props = cadenceProperties.getDomains().get("default");

        RegisterDomainRequest request = new RegisterDomainRequest();
        new Customizers<>(builderCustomizers).customize(request, props);

        try {
            workflowService.RegisterDomain(request);
            log.info("Domain '{}' successfully registered", request.getName());
        } catch (DomainAlreadyExistsError e) {
            log.info("Domain '{}' already exists", request.getName());
        }
    }
}
