package pl.babiak.ruslana.customer.project.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.babiak.ruslana.customer.project.exception.OrderNotFoundException;
import pl.babiak.ruslana.customer.project.model.Order;
import pl.babiak.ruslana.customer.project.service.OrderService;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/add/newOrder")
    public void addOrder(@RequestBody Order order) {
        orderService.addOrder(order);
    }

    @GetMapping("/order{id}")
    public Order getOrder(@PathVariable long id) throws OrderNotFoundException {
        return orderService.getOrder(id);
    }

    @GetMapping("/order/list")
    public List<Order> getOrderList() {
        return orderService.getOrderList();
    }

    @PostMapping("/update/order{id}")
    public Order updateOrder(@PathVariable long id, @RequestBody Order order) throws OrderNotFoundException {
        return orderService.updateOrder(id, order);
    }

    @DeleteMapping("/delete{id}")
    public void deleteOrder(@Valid @RequestParam long id) {
        orderService.deleteOrder(id);
    }
}
