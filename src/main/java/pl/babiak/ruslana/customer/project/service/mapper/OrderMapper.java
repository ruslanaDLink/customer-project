package pl.babiak.ruslana.customer.project.service.mapper;

import org.springframework.stereotype.Component;
import pl.babiak.ruslana.customer.project.model.Order;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.entity.OrderEntity;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapper {

    public List<Product> mapToProduct(List<ProductEntity> productEntities) {
        List<Product> products = new ArrayList<>();
        Product product;
        for (ProductEntity productEntity : productEntities) {
            product = new Product(productEntity.getId(), productEntity.getProduct(), productEntity.getCost());
            products.add(product);
        }
        return products;
    }

    public static List<ProductEntity> mapToEntity(List<Product> products) {
        List<ProductEntity> productEntities = new ArrayList<>();
        ProductEntity productEntity = new ProductEntity();
        for (Product product : products) {
            productEntity.setId(product.getId());
            productEntity.setProduct(product.getProduct());
            productEntity.setCost(product.getCost());
            productEntities.add(productEntity);
        }
        return productEntities;
    }

    public Order map(OrderEntity entity) {
        return new Order(entity.getId(), mapToProduct(entity.getProducts()), entity.getDate());
    }

    public OrderEntity map(Order order) {
        OrderEntity entity = new OrderEntity();
        entity.setId(order.getId());
        entity.setProducts(mapToEntity(order.getProducts()));
        entity.setDate(order.getDate());
        return entity;
    }
}
