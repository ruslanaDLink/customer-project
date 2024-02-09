package pl.babiak.ruslana.customer.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.exception.CustomerNotFoundException;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.CustomerRepository;
import pl.babiak.ruslana.customer.project.service.CustomerService;
import pl.babiak.ruslana.customer.project.service.mapper.CustomerMapper;

import java.util.List;

@SpringBootTest
class CustomerControllerTest {
    @Autowired
    private CustomerRepository repository;

    private CustomerMapper mapper;
    private CustomerService service;
    private CustomerController controller;

    @BeforeEach
    void setUp() {
        mapper = new CustomerMapper();
        service = new CustomerService(repository, mapper);
        controller = new CustomerController(service);
    }

    @Test
    public void create() {
        //given
        Customer customer = new Customer(81274, "Oscar Carter", "oskarcarter@gmail.com", "RH10");

        //when
        Customer addedCustomer = controller.addCustomer(customer);

        //then
        Assertions.assertNotNull(addedCustomer, "Insertion failed.");
    }

    @Test
    public void read() throws CustomerNotFoundException {
        //given
        Customer customer = new Customer(81274, "Oscar Carter", "oskarcarter@gmail.com", "RH10");

        //when
        Customer controllerCustomer = controller.getCustomer(customer.getId());
        List<Customer> allCustomers = controller.getAllCustomers();

        //then
        Assertions.assertAll(
                () -> Assertions.assertThrows(CustomerNotFoundException.class,
                        () -> controller.getCustomer(controllerCustomer.getId())),
                () -> Assertions.assertNotNull(allCustomers, "Customer list is empty.")
        );
    }

    @Test
    public void update() throws CustomerNotFoundException {
        //given
        Customer customer1 = new Customer(81274, "Oscar Carter", "oskarcarter@gmail.com", "RH10");
        Customer customer2 = new Customer(81274, "Oscar Carter", "oskarcarter2003@gmail.com", "RH10");

        //when
        Customer addedCustomer = controller.addCustomer(customer1);
        Customer updatedCustomer = controller.updateCustomer(customer2, addedCustomer.getId());

        String originalEmail = addedCustomer.getEmail();
        String updatedEmail = updatedCustomer.getEmail();

        //then
        Assertions.assertAll(
                () -> Assertions.assertEquals(updatedCustomer.getId(), addedCustomer.getId(), "Failed to update customer."),
                () -> Assertions.assertNotEquals(originalEmail, updatedEmail, "Failed to update email.")
        );
    }

    @Test
    public void delete() {
        //given
        Customer customer = new Customer(81274, "Oscar Carter", "oskarcarter2003@gmail.com", "RH10");

        //when
        controller.deleteCustomer(customer.getId());
        List<Customer> customerList = controller.getAllCustomers();

        //then
        Assertions.assertFalse(customerList.contains(customer), "Failed to delete customer.");
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        service = null;
        controller = null;
    }
}