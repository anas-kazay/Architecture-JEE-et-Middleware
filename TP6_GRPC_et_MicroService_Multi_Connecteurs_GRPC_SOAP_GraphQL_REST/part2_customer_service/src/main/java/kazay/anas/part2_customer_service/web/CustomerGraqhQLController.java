package kazay.anas.part2_customer_service.web;

import kazay.anas.part2_customer_service.dto.CustomerRequest;
import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.mappers.CustomerMapper;
import kazay.anas.part2_customer_service.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@AllArgsConstructor
public class CustomerGraqhQLController {
    private CustomerRepository customerRepository;
    private CustomerMapper customerMapper;

    @QueryMapping
    public List<Customer> allCustomers(){
        return customerRepository.findAll();
    }

    @QueryMapping
    public Customer customerById(@Argument Long id){
        Customer customer= customerRepository.findById(id).orElse(null);
        if(customer==null){
            throw new RuntimeException("Customer not found");
        }
        return customer;
    }

    @MutationMapping
    public Customer saveCustomer(@Argument CustomerRequest customer){
        Customer c = customerMapper.from(customer);
        return customerRepository.save(c);
    }

}
