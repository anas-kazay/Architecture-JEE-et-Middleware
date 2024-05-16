package kazay.anas.part2_customer_service.config;

import kazay.anas.part2_customer_service.web.CustomerSoapService;
import lombok.AllArgsConstructor;
import org.apache.cxf.Bus;
import org.apache.cxf.jaxws.EndpointImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@AllArgsConstructor
public class CxfConfig {
    private Bus bus;
    private CustomerSoapService customerSoapService;

    @Bean
    public EndpointImpl endpoint() {
        EndpointImpl endpoint = new EndpointImpl(bus, customerSoapService);
        endpoint.publish("/CustomerService");
        return endpoint;
    }
}
