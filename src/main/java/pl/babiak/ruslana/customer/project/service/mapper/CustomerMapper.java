package pl.babiak.ruslana.customer.project.service.mapper;

import org.springframework.stereotype.Component;
import pl.babiak.ruslana.customer.project.model.Customer;
import pl.babiak.ruslana.customer.project.repository.entity.CustomerEntity;

@Component
public class CustomerMapper {

    public Customer map(CustomerEntity entity) {
        return new Customer(entity.getId(), entity.getName(), entity.getEmail(), entity.getPostalCode());
    }

    public CustomerEntity map(Customer customer) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(customer.getId());
        customerEntity.setName(customer.getName());
        customerEntity.setEmail(customer.getEmail());
        customerEntity.setPostalCode(customer.getPostalCode());
        return customerEntity;
    }
}
