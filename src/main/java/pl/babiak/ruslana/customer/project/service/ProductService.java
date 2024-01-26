package pl.babiak.ruslana.customer.project.service;

import org.springframework.stereotype.Service;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Optional<Product> getProduct(long id) {
        return productRepository.findById(id);
    }

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }
}
