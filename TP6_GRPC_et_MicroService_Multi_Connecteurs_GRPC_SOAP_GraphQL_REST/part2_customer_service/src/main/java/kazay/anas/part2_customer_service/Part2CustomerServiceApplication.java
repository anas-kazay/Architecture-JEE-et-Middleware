package kazay.anas.part2_customer_service;

import kazay.anas.part2_customer_service.entities.Customer;
import kazay.anas.part2_customer_service.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Part2CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(Part2CustomerServiceApplication.class, args);
    }


    @Bean
    CommandLineRunner start(CustomerRepository customerRepository){
        return args -> {
            customerRepository.save(Customer.builder().name("Anas").email("anas@gmail.com").build());
            customerRepository.save(Customer.builder().name("Oussama").email("oussama@gmail.com").build());
            customerRepository.save(Customer.builder().name("Fatima").email("fatima@gmail.com").build());
        };
    }
}
