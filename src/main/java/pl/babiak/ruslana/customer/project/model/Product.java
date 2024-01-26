package pl.babiak.ruslana.customer.project.model;

public class Product {
    private long id;
    private String product;
    private double cost;

    public Product(long id, String product, double cost) {
        this.id = id;
        this.product = product;
        this.cost = cost;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", product='" + product + '\'' +
                ", cost=" + cost +
                '}';
    }
}
