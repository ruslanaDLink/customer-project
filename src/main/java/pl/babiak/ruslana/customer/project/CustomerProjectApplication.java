package pl.babiak.ruslana.customer.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.CustomerRepository;
import pl.babiak.ruslana.customer.project.service.mapper.CustomerMapper;

@SpringBootApplication
public class CustomerProjectApplication {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerProjectApplication.class, args);
    }

    @Bean
    CommandLineRunner insertNewCustomers() {
        return args -> {
            Customer isabelSmith = new Customer(
                    46742,
                    "Isabel Smith",
                    "isabella23@gmail.com",
                    "GU16 7HF");
            Customer angelaWilliams = new Customer(
                    23552,
                    "Angela Williams",
                    "angela.williams@gmail.com",
                    "SW1W 0NY");
            Customer michaelJohns = new Customer(
                    56832,
                    "Michael Johns",
                    "micho973@gmail.com",
                    "L1 8JQ");

            CustomerMapper mapper = new CustomerMapper();
            customerRepository.save(mapper.map(isabelSmith));
            customerRepository.save(mapper.map(angelaWilliams));
            customerRepository.save(mapper.map(michaelJohns));
            System.out.println("Added new customers!");
        };
    }
}
