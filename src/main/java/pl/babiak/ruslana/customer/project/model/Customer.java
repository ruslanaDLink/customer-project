package pl.babiak.ruslana.customer.project.model;

public class Customer {
    private long id;
    private String name;
    private String email;
    private String postalCode;

    public Customer(long id, String name, String email, String postalCode) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.postalCode = postalCode;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", postalCode='" + postalCode + '\'' +
                '}';
    }
}
