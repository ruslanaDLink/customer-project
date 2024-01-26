package pl.babiak.ruslana.customer.project.model;

import java.time.LocalDateTime;

public class Order {
    private long id;
    private LocalDateTime date;
    private double price;

    public Order(long id, LocalDateTime date, double price) {
        this.id = id;
        this.date = date;
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
}
