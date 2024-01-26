package pl.babiak.ruslana.customer.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.service.ProductService;

import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@RequestParam long id) {
        Optional<Product> product = productService.getProduct(id);
        return product.orElseThrow();
    }

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
