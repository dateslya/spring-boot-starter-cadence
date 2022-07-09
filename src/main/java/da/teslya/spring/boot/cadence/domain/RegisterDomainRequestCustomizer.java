package da.teslya.spring.boot.cadence.domain;

import com.uber.cadence.RegisterDomainRequest;

import java.util.function.BiConsumer;

@FunctionalInterface
public interface RegisterDomainRequestCustomizer extends
        BiConsumer<RegisterDomainRequest, DomainProperties> {

}
