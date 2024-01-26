package pl.babiak.ruslana.customer.project.model;

import java.time.LocalDateTime;
import java.util.List;

public class Order {
    private long id;
    private List<Product> products;
    private LocalDateTime date;
    private double price;

    public Order(long id, List<Product> products, LocalDateTime date) {
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
                ", date=" + date +
                ", price=" + price +
                ", products=" + products +
                '}';
    }
}
