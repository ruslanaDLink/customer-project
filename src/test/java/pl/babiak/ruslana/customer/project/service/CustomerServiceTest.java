package pl.babiak.ruslana.customer.project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.exception.CustomerNotFoundException;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.CustomerRepository;
import pl.babiak.ruslana.customer.project.repository.entity.CustomerEntity;
import pl.babiak.ruslana.customer.project.service.mapper.CustomerMapper;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerRepository repository;
    private CustomerService service;
    private CustomerMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper();
        service = new CustomerService(repository, mapper);
    }

    @Test
    public void create() {
        //given
        Customer customer = new Customer(81274, "Audrey Sheridan", "aud67@gmail.com", "RH10");

        //when
        Customer addedCustomer = service.addCustomer(customer);

        //then
        Assertions.assertNotNull(addedCustomer, "Failed to add customer.");

    }

    @Test
    public void read() throws CustomerNotFoundException {
        //given
        Customer newCustomer = new Customer(84897, "Russel Mae", "mae.russel@gmail.com", "SO16");

        //when
        Customer serviceCustomer = service.getCustomer(newCustomer.getId());
        Customer customer = service.getCustomer(serviceCustomer.getId());

        //then
        Assertions.assertThrows(CustomerNotFoundException.class, () -> service.getCustomer(customer.getId()));
    }

    @Test
    public void update() throws CustomerNotFoundException {
        //given
        Customer customer1 = new Customer(84897, "Deborah Abney", "deborah.abney@gmail.com", "SE1P");
        Customer customer2 = new Customer(84897, "Deborah Abney", "deborah.abney@gmail.com", "DN16");

        //when
        Customer addedCustomer = service.addCustomer(customer1);
        Customer updatedCustomer = service.updateCustomer(customer2, addedCustomer.getId());

        //then
        Assertions.assertEquals(updatedCustomer.getId(), addedCustomer.getId(), "Failed to update.");
    }

    @Test
    public void delete() {
        //given
        Customer customer = new Customer(84897, "Deborah Abney", "deborah.abney@gmail.com", "DN16");

        //when
        Customer addedCustomer = service.addCustomer(customer);
        service.deleteCustomer(addedCustomer.getId());
        Optional<CustomerEntity> optionalCustomer = repository.findById(addedCustomer.getId());
        boolean isPresent = optionalCustomer.isPresent();
        List<Customer> customerList = service.getAllCustomers();

        //then
        Assertions.assertAll(
                () -> Assertions.assertFalse(isPresent, "Failed to delete customer."),
                () -> Assertions.assertFalse(customerList.contains(addedCustomer), "Failed to delete customer.")
        );
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        service = null;
    }
}