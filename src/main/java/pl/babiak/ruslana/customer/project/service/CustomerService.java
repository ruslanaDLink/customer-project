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
    private CustomerMapper customerMapper;

    public CustomerService(CustomerRepository customerRepository, CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    public Customer addCustomer(Customer customer) {
        CustomerEntity entity = customerRepository.save(customerMapper.map(customer));
        return customerMapper.map(entity);
    }

    public Customer getCustomer(long id) throws CustomerNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        CustomerEntity customerEntity = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException("Failed to get customer: " + id)
        );
        return customerMapper.map(customerEntity);
    }

    public List<Customer> getAllCustomers() {
        List<CustomerEntity> entityCustomers = customerRepository.findAll();
        List<Customer> customers = new ArrayList<>();
        entityCustomers.forEach(x -> customers.add(customerMapper.map(x)));
        return customers;
    }

    public Customer updateCustomer(Customer customer, long id) throws CustomerNotFoundException {
        Optional<CustomerEntity> optionalCustomer = customerRepository.findById(id);
        CustomerEntity customerEntity = optionalCustomer.orElseThrow(
                () -> new CustomerNotFoundException("Failed to search customer by id " + id)
        );
        Customer updatedCustomer = customerMapper.map(customerEntity);
        updatedCustomer.setId(customer.getId());
        updatedCustomer.setName(customer.getName());
        updatedCustomer.setPostalCode(customer.getPostalCode());
        updatedCustomer.setEmail(customer.getEmail());
        return updatedCustomer;
    }

    public void deleteCustomer(long id) {
        customerRepository.deleteById(id);
    }
}
