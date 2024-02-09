package pl.babiak.ruslana.customer.project.service.mapper;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;

class ProductMapperTest {
    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper();
    }

    @Test
    void mapToProduct() {
        //given
        ProductEntity entity = new ProductEntity();
        entity.setId(12345);
        entity.setProduct("Tiny Dog Statue");
        entity.setCost(35.00);

        //when
        Product mappedProduct = mapper.map(entity);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(mappedProduct, "Failed to map entity."),
                () -> Assertions.assertEquals(entity.getId(), mappedProduct.getId(), "Failed to map. Ids are different.")
        );
    }

    @Test
    void mapToEntity() {
        //given
        Product product = new Product(34511, "Black-Red Brooch", 280.00);

        //when
        ProductEntity entity = mapper.map(product);

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotNull(entity, "Failed to map product."),
                () -> Assertions.assertEquals(entity.getId(), product.getId(), "Failed to map. Ids are different.")
        );
    }
}