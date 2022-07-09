package da.teslya.spring.boot.cadence.domain;

import lombok.Data;

@Data
public class DomainProperties {

    private String name = "default_domain";
    private String description = "Default domain";
}
