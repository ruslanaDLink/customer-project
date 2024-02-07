package pl.babiak.ruslana.customer.project.service;

import org.springframework.stereotype.Service;
import pl.babiak.ruslana.customer.project.exception.OrderNotFoundException;
import pl.babiak.ruslana.customer.project.model.Order;
import pl.babiak.ruslana.customer.project.repository.OrderRepository;
import pl.babiak.ruslana.customer.project.repository.entity.OrderEntity;
import pl.babiak.ruslana.customer.project.service.mapper.OrderMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    private OrderRepository orderRepository;
    private OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public Order addOrder(Order order) {
        OrderEntity entity = orderRepository.save(orderMapper.map(order));
        return orderMapper.map(entity);
    }

    public Order getOrder(long id) throws OrderNotFoundException {
        Optional<OrderEntity> orderById = orderRepository.findById(id);
        OrderEntity orderEntity = orderById.orElseThrow(
                () -> new OrderNotFoundException("Failed to get order: " + id)
        );
        return orderMapper.map(orderEntity);
    }

    public List<Order> getOrderList() {
        List<OrderEntity> entities = orderRepository.findAll();
        List<Order> orders = new ArrayList<>();
        entities.forEach(x -> orders.add(orderMapper.map(x)));
        return orders;
    }

    public Order updateOrder(long id, Order order) throws OrderNotFoundException {
        Optional<OrderEntity> optionalOrder = orderRepository.findById(id);
        OrderEntity entity = optionalOrder.orElseThrow(
                () -> new OrderNotFoundException("Impossible to update. Id's not found " + id));
        Order updatedOrder = orderMapper.map(entity);
        updatedOrder.setId(order.getId());
        updatedOrder.setProducts(order.getProducts());
        updatedOrder.setDate(order.getDate());
        return updatedOrder;
    }

    public void deleteOrder(long id) {
        orderRepository.deleteById(id);
    }
}
