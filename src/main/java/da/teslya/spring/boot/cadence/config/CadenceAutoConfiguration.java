package da.teslya.spring.boot.cadence.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan("da.teslya.spring.boot.cadence")
@EnableConfigurationProperties(CadenceProperties.class)
@Configuration
public class CadenceAutoConfiguration {

}
