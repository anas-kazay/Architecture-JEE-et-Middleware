package kazay.anas.part2_customer_service.mappers;

import kazay.anas.part2_customer_service.dto.CustomerRequest;
import kazay.anas.part2_customer_service.entities.Customer;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapper {
    private ModelMapper modelMapper=new ModelMapper();
    public Customer from(CustomerRequest customerRequest) {

        return modelMapper.map(customerRequest, Customer.class);

    }
}
