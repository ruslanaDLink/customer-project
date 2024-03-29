package pl.babiak.ruslana.customer.project.controller;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.babiak.ruslana.customer.project.exception.ProductNotFoundException;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newProduct")
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping("/update/{id}")
    public Product updateProduct(@RequestBody Product product, @PathVariable long id) throws ProductNotFoundException {
        return productService.updateProduct(product, id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteProduct(@Valid @RequestParam("id") long id) {
        productService.deleteProduct(id);
    }
}
