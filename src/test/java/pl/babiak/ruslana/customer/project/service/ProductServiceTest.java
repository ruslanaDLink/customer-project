package pl.babiak.ruslana.customer.project.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.exception.ProductNotFoundException;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.ProductRepository;
import pl.babiak.ruslana.customer.project.service.mapper.ProductMapper;

import java.util.List;

@SpringBootTest
class ProductServiceTest {

    @Autowired
    private ProductRepository repository;

    private ProductMapper mapper;
    private ProductService service;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper();
        service = new ProductService(repository, mapper);
    }

    @Test
    public void create() throws ProductNotFoundException {
        //given
        Product product = new Product(7656, "Bird Cage", 105.50);

        //when
        Product addedProduct = service.addProduct(product);

        //then
        Assertions.assertNotNull(service.getProduct(addedProduct.getId()), "create() method failed to create a new product.");
    }

    @Test
    public void read() throws ProductNotFoundException {
        //given
        Product newProduct = new Product(253L, "Dyson", 500.00);

        //when
        Product addedProduct = service.addProduct(newProduct);
        Product product = service.getProduct(addedProduct.getId());

        //then
        Assertions.assertThrows(ProductNotFoundException.class, () -> service.getProduct(product.getId()));
    }

    @Test
    public void update() throws ProductNotFoundException {
        //given
        Product product1 = new Product(3751, "Toy for kids: Dinosaur", 89.99);
        Product product2 = new Product(3751, "Toy for kids: Dinosaur", 75.50);

        //when
        Product addedProduct = service.addProduct(product1);
        Product updatedProduct = service.updateProduct(product2, addedProduct.getId());

        //then
        Assertions.assertAll(
                () -> Assertions.assertNotEquals(addedProduct.getCost(), updatedProduct.getCost(),
                        "Product is not updated."),
                () -> Assertions.assertEquals(updatedProduct.getId(), addedProduct.getId(),
                        "Failed to update. Customers with different ids.")
        );
    }

    @Test
    public void delete() {
        //given
        Product product = new Product(3751, "Toy for kids: Dinosaur", 75.50);

        //when
        service.deleteProduct(product.getId());

        List<Product> productList = service.getAllProducts();
        boolean contains = productList.contains(product);

        //then
        Assertions.assertFalse(contains, "Failed to delete product.");
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        service = null;
    }
}
