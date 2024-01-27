package pl.babiak.ruslana.customer.project.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;
import pl.babiak.ruslana.customer.project.service.ProductService;
import pl.babiak.ruslana.customer.project.service.mapper.ProductMapper;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/products")
public class ProductController {
    private ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/newProduct")
    public void addProduct(@Valid @RequestBody ProductEntity product) {
        ProductMapper mapper = new ProductMapper();
        mapper.map(product);
        productService.addProduct(product);
    }

    @GetMapping("/product/{id}")
    public Product getProduct(@PathVariable long id) throws ProductNotFoundException {
        return productService.getProduct(id);
    }

    @GetMapping("/allProducts")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @DeleteMapping("/products/delete/{id}")
    public void deleteProduct(@RequestParam("id") long id) {
        productService.deleteProduct(id);
    }
}
