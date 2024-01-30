package pl.babiak.ruslana.customer.project.exception;

public class OrderNotFoundException extends Exception {
    public OrderNotFoundException(String message) {
        super(message);
    }
}
