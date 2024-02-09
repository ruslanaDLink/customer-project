package pl.babiak.ruslana.customer.project.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.babiak.ruslana.customer.project.exception.ProductNotFoundException;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.ProductRepository;
import pl.babiak.ruslana.customer.project.service.ProductService;
import pl.babiak.ruslana.customer.project.service.mapper.ProductMapper;

import java.util.List;

@SpringBootTest
public class ProductControllerTest {
    @Autowired
    private ProductRepository repository;

    private ProductController controller;
    private ProductService service;
    private ProductMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ProductMapper();
        service = new ProductService(repository, mapper);
        controller = new ProductController(service);
    }

    @Test
    public void create() throws ProductNotFoundException {
        //given
        Product product = new Product(65234, "Fake Lizard", 19.99);

        //when
        Product addedProduct = controller.addProduct(product);

        //then
        Assertions.assertNotNull(controller.getProduct(addedProduct.getId()));
    }

    @Test
    public void read() {
        //given
        Product product = new Product(65235, "Barber Scissors", 69.99);

        //when
        Product addedProduct = controller.addProduct(product);
        List<Product> productList = controller.getAllProducts();

        //then
        Assertions.assertAll(
                () -> Assertions.assertThrows
                        (ProductNotFoundException.class, () -> controller.getProduct(addedProduct.getId())),
                () -> Assertions.assertNotNull(productList, "Product list is empty.")
        );

    }

    @Test
    public void update() throws ProductNotFoundException {
        //given
        Product product1 = new Product(65235, "Barber Scissors", 69.99);
        Product product2 = new Product(65235, "Barber Scissors", 79.99);

        //when
        Product updatedProduct = controller.updateProduct(product2, product1.getId());

        //then
        Assertions.assertNotEquals(product1.getCost(), updatedProduct.getCost(), "Product failed to update.");
    }

    @Test
    public void delete() {
        //given
        Product product = new Product(65235, "Barber Scissors", 79.99);

        //when
        controller.deleteProduct(product.getId());
        List<Product> productList = controller.getAllProducts();

        //then
        Assertions.assertFalse(productList.contains(product), "Product still exists.");
    }

    @AfterEach
    void tearDown() {
        mapper = null;
        service = null;
        controller = null;
    }
}