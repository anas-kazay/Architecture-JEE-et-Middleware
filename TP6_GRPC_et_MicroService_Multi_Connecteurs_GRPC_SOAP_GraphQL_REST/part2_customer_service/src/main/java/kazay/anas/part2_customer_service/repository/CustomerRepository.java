package kazay.anas.part2_customer_service.repository;

import kazay.anas.part2_customer_service.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
