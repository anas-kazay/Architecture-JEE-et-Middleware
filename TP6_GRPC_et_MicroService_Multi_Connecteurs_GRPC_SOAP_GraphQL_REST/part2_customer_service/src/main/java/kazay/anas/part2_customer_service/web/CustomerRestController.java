package kazay.anas.part2_customer_service.web;

import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor
public class CustomerRestController {
    private CustomerRepository customerRepository;

    @GetMapping("/customers")
    public List<Customer> customerList(){
        return customerRepository.findAll();
    }

    @GetMapping("/customers/{id}")
    public Customer customerById(@PathVariable Long id){
        return customerRepository.findById(id).orElse(null);
    }

    @PostMapping("/customers")
    public Customer saveCustomer(@RequestBody Customer customer){
        return customerRepository.save(customer);
    }
}
