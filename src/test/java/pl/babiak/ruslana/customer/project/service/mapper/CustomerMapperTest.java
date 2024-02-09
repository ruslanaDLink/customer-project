package pl.babiak.ruslana.customer.project.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.entity.CustomerEntity;

@SpringBootTest
class CustomerMapperTest {
    private CustomerMapper mapper;

    @Test
    void mapToCustomer() {
        //given
        mapper = new CustomerMapper();
        CustomerEntity entity = new CustomerEntity();

        //when
        Customer customer = mapper.map(entity);

        //then
        Assertions.assertEquals(entity.getId(), customer.getId(), "Failed to map entity.");
    }

    @Test
    void mapToEntity() {
        //given
        mapper = new CustomerMapper();
        Customer customer = new Customer(84897, "Russel Mae", "mae.russel@gmail.com", "SO16");

        //when
        CustomerEntity entity = mapper.map(customer);

        //then
        Assertions.assertEquals(entity.getId(), customer.getId(), "Failed to map customer.");
    }
}