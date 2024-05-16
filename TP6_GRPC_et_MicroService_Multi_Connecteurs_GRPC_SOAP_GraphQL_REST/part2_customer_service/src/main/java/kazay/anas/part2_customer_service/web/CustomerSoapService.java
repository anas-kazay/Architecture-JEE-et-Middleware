package kazay.anas.part2_customer_service.web;

import jakarta.jws.WebMethod;
import jakarta.jws.WebParam;
import jakarta.jws.WebService;
import kazay.anas.part2_customer_service.dto.CustomerRequest;
import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.mappers.CustomerMapper;
import kazay.anas.part2_customer_service.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@AllArgsConstructor
@WebService(serviceName = "CustomerWS")
public class CustomerSoapService {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @WebMethod
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @WebMethod
    public Customer customerById(@WebParam(name = "id") Long id){
        return customerRepository.findById(id).orElse(null);
    }

    @WebMethod
    public Customer saveCustomer(@WebParam(name = "customer") CustomerRequest customerRequest){
        return customerRepository.save(customerMapper.from(customerRequest));
    }
}
