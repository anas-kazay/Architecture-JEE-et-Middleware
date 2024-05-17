package kazay.anas.part2_customer_service.mappers;

import kazay.anas.part2_customer_service.dto.CustomerRequest;
import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.stub.CustomerServiceOuterClass;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Customer from(CustomerRequest customerRequest) {

        return modelMapper.map(customerRequest, Customer.class);

    }

    public CustomerServiceOuterClass.Customer fromCustomer(Customer customer) {
        return modelMapper.map(customer, CustomerServiceOuterClass.Customer.Builder.class).build();
    }

    public Customer fromCustomerRequest(CustomerServiceOuterClass.CustomerRequest customer) {
        return modelMapper.map(customer, Customer.class);
    }
}
