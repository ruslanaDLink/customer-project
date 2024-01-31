package pl.babiak.ruslana.customer.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private long id;
    private Customer customer;
    private List<Product> products;
    private LocalDateTime date;
    private double price;

    public Order(long id, Customer customer, List<Product> products, LocalDateTime date) {
        this.customer = customer;
        this.products = products;
        this.id = id;
        this.date = date;
        for (Product product : products) {
            price += product.getCost();
        }
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", customer=" + customer +
                ", products=" + products +
                ", date=" + date +
                ", price=" + price +
                '}';
    }
}
