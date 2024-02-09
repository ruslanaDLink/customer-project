package pl.babiak.ruslana.customer.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import pl.babiak.ruslana.customer.project.repository.entity.CustomerEntity;

import java.util.Optional;

@ContextConfiguration
@SpringBootTest
class CustomerRepositorySpringTest {
    @Autowired
    private CustomerRepository repository;

    @Test
    public void create() {
        //given
        CustomerEntity customerEntity = new CustomerEntity();

        //when
        CustomerEntity savedCustomer = repository.save(customerEntity);

        //then
        Assertions.assertNotNull(savedCustomer, "Insertion failed");
    }

    @Test
    public void read() {
        //given
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(56742);
        customerEntity.setName("Alice Debby");
        customerEntity.setEmail("aliali@gmail.com");
        customerEntity.setPostalCode("SO15");

        //when
        CustomerEntity savedCustomer = repository.save(customerEntity);
        Optional<CustomerEntity> optionalCustomer = repository.findById(savedCustomer.getId());

        //then
        Assertions.assertNotNull(optionalCustomer, "Customer not found.");
    }

    @Test
    public void update() {
        //given
        CustomerEntity customerEntity1 = new CustomerEntity();
        customerEntity1.setId(56742);
        customerEntity1.setName("Alice Debby");
        customerEntity1.setEmail("aliali@gmail.com");
        customerEntity1.setPostalCode("SO15");

        CustomerEntity customerEntity2 = new CustomerEntity();
        customerEntity2.setId(56742);
        customerEntity2.setName("Alice Debby");
        customerEntity2.setEmail("aliali@gmail.com");
        customerEntity2.setPostalCode("SO16");

        //when
        CustomerEntity savedCustomer = repository.save(customerEntity1);
        customerEntity2.setId(savedCustomer.getId());
        CustomerEntity updatedCustomer = repository.save(customerEntity2);

        //then
        Assertions.assertEquals(updatedCustomer.getId(), savedCustomer.getId(), "Failed to update customer.");
    }

    @Test
    public void delete() {
        //given
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(56742);
        customerEntity.setName("Alice Debby");
        customerEntity.setEmail("aliali@gmail.com");
        customerEntity.setPostalCode("SO16");

        //when
        CustomerEntity savedCustomer = repository.save(customerEntity);
        repository.deleteById(savedCustomer.getId());
        boolean isPresent = repository.findById(customerEntity.getId()).isPresent();

        //then
        Assertions.assertFalse(isPresent, "Failed to delete customer.");
    }
}