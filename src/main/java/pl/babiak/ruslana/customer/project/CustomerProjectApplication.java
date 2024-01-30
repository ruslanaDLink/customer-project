package pl.babiak.ruslana.customer.project;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.CustomerRepository;
import pl.babiak.ruslana.customer.project.service.mapper.CustomerMapper;

@SpringBootApplication
public class CustomerProjectApplication implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    public static void main(String[] args) {
        SpringApplication.run(CustomerProjectApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        Customer laurenBrown = new Customer(
                879432,
                "Lauren Brown",
                "lauri__1996@gmail.com",
                "W1A 1AA");
        Customer perryClark = new Customer(
                56752,
                "Perry Clark",
                "perry-parrot@gmail.com",
                "TS19 0AP");
        Customer annaRobinson = new Customer(
                56411,
                "Anna Robinson",
                "aneetrobi@gmail.com",
                "A11 B12");

        CustomerMapper mapper = new CustomerMapper();
        customerRepository.save(mapper.map(laurenBrown));
        customerRepository.save(mapper.map(perryClark));
        customerRepository.save(mapper.map(annaRobinson));
        System.out.println("Added new customers!");
    }
}
