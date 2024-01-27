package pl.babiak.ruslana.customer.project.service;

import org.springframework.stereotype.Service;
import pl.babiak.ruslana.customer.project.exception.CustomerNotFoundException;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.CustomerRepository;
import pl.babiak.ruslana.customer.project.repository.entity.CustomerEntity;
import pl.babiak.ruslana.customer.project.service.mapper.CustomerMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {
    private CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomer(long id) throws CustomerNotFoundException {
        CustomerMapper mapper = new CustomerMapper();
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        CustomerEntity customerEntity = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException("Customer doesn't exist.")
        );
        return mapper.map(customerEntity);
    }

    public List<Customer> getAllCustomers() {
        List<CustomerEntity> entityCustomers = customerRepository.findAll();
        CustomerMapper mapper = new CustomerMapper();
        List<Customer> customers = new ArrayList<>();
        entityCustomers.forEach(x -> customers.add(mapper.map(x)));
        return customers;
    }

    public Customer updateCustomer(Customer customer) {
        CustomerMapper mapper = new CustomerMapper();
        CustomerEntity savedCustomer = customerRepository.save(mapper.map(customer));
        return mapper.map(savedCustomer);
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }
}
