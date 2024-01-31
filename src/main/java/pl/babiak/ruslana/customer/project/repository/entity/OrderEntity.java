package pl.babiak.ruslana.customer.project.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import pl.babiak.ruslana.customer.project.model.Customer;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
@Table(name = "ORDERS")
public class OrderEntity {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    @JoinColumn(name = "CUSTOMER", referencedColumnName = "id")
    private CustomerEntity customer;
    @OneToMany
    @JoinColumn(name = "PRODUCT_IN_LIST", referencedColumnName = "id")
    private List<ProductEntity> products;
    @Column(name = "DATE")
    private LocalDateTime date;
    @Column(name = "PRICE")
    private double price;

}
