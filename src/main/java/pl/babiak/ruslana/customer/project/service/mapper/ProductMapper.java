package pl.babiak.ruslana.customer.project.service.mapper;

import org.springframework.stereotype.Component;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;

@Component
public class ProductMapper {

    public Product map(ProductEntity entity) {
        return new Product(entity.getId(), entity.getProduct(), entity.getCost());
    }

    public ProductEntity map(Product product) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(product.getId());
        productEntity.setProduct(product.getProduct());
        productEntity.setCost(product.getCost());
        return productEntity;
    }
}
