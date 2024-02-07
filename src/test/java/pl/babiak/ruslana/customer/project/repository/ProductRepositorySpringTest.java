package pl.babiak.ruslana.customer.project.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;

import java.util.Optional;

@SpringBootTest
public class ProductRepositorySpringTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void create() {
        //given
        ProductEntity product = new ProductEntity();
        product.setProduct("Engaging Algebra Book: 4th Edition");
        product.setCost(130.00);

        //when
        ProductEntity savedEntity = productRepository.save(product);

        //then
        Assertions.assertNotNull(savedEntity, "Saved entity is null.");
    }

    @Test
    public void read() {
        //given
        ProductEntity product = new ProductEntity();
        product.setProduct("Astronomy for kids: 1th Edition");
        product.setCost(50.00);

        //when
        ProductEntity savedProduct = productRepository.save(product);
        Optional<ProductEntity> optionalProduct = productRepository.findById(savedProduct.getId());

        //then
        Assertions.assertNotNull(optionalProduct, "Empty product.");
    }

    @Test
    public void update() {
        //given
        ProductEntity product = new ProductEntity();
        product.setProduct("Notebook");
        product.setCost(15.00);
        double previousCost = product.getCost();

        //when
        ProductEntity savedProduct = productRepository.save(product);
        savedProduct.setCost(17.50);
        double changedCost = savedProduct.getCost();

        //then
        Assertions.assertNotEquals(previousCost, changedCost, "Failed to update price.");

    }

    @Test
    public void delete() {
        //given
        ProductEntity product = new ProductEntity();
        product.setProduct("Notebook");
        product.setCost(15.00);

        //when
        ProductEntity savedProduct = productRepository.save(product);
        productRepository.delete(savedProduct);
        Optional<ProductEntity> deletedProduct = productRepository.findById(savedProduct.getId());
        boolean isExist = deletedProduct.isPresent();

        //then
        Assertions.assertFalse(isExist, "Failed to delete product.");
    }
}
