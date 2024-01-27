package pl.babiak.ruslana.customer.project.service.mapper;

import org.springframework.stereotype.Component;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;

@Component
public class ProductMapper {

    public Product map(ProductEntity entity) {
        return new Product(entity.getId(), entity.getProduct(), entity.getCost());
    }

    public ProductEntity map(Product entity) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(entity.getId());
        productEntity.setProduct(entity.getProduct());
        productEntity.setCost(entity.getCost());
        return productEntity;
    }
}
