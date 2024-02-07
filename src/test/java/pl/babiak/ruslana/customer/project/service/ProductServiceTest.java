package pl.babiak.ruslana.customer.project.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.ProductRepository;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;
import pl.babiak.ruslana.customer.project.service.mapper.ProductMapper;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository repository;

    private ProductMapper mapper;
    private ProductService service;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper();
        service = new ProductService(repository, mapper);

    }

    @Test
    public void create() {
        //given
        Product product = new Product(7656, "Bird Cage", 105.50);
        ProductEntity entity = mapper.map(product);

        //when
        service.addProduct(product);

        //then
        ArgumentCaptor<ProductEntity> productCaptor = ArgumentCaptor.forClass(ProductEntity.class);
        Mockito.verify(repository).save(productCaptor.capture());
        ProductEntity productEntity = productCaptor.getValue();
        boolean isIdEqual = productEntity.getId() == entity.getId();
        boolean isProductEqual = productEntity.getProduct().equals(entity.getProduct());
        boolean isCostEqual = productEntity.getCost() == entity.getCost();

        Assertions.assertAll(
                () -> Assertions.assertNotNull(productEntity, "Failed insertion."),
                () -> Assertions.assertTrue(isIdEqual, "Creating failed with id."),
                () -> Assertions.assertTrue(isProductEqual, "Creating failed with product's name."),
                () -> Assertions.assertTrue(isCostEqual, "Creating failed with cost.")
        );
    }

    public void read() {

    }

    public void update() {

    }

    public void delete() {

    }


}