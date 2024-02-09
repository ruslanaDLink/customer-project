package pl.babiak.ruslana.customer.project.service;

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
        service.addProduct(product);

        //then
        Assertions.assertNotNull(service.getProduct(product.getId()), "create() method failed to create a new product.");
    }

    @Test
    public void read() throws ProductNotFoundException {
        //given
        Product newProduct = new Product(253L, "Dyson", 500.00);

        //when
        service.addProduct(newProduct);
        Product product = service.getProduct(newProduct.getId());

        //then
        Assertions.assertThrows(ProductNotFoundException.class, () -> service.getProduct(product.getId()));
    }

    @Test
    public void update() throws ProductNotFoundException {
        //given
        Product product1 = new Product(3751, "Toy for kids: Dinosaur", 89.99);
        Product product2 = new Product(3751, "Toy for kids: Dinosaur", 75.50);

        //when
        service.addProduct(product1);
        Product updatedProduct = service.updateProduct(product2, product1.getId());

        //then
        Assertions.assertNotEquals(product1.getCost(), updatedProduct.getCost(), "Product is not updated.");
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
}
