package pl.babiak.ruslana.customer.project.service;

import org.springframework.stereotype.Service;
import pl.babiak.ruslana.customer.project.exception.ProductNotFoundException;
import pl.babiak.ruslana.customer.project.model.Product;
import pl.babiak.ruslana.customer.project.repository.ProductRepository;
import pl.babiak.ruslana.customer.project.repository.entity.ProductEntity;
import pl.babiak.ruslana.customer.project.service.mapper.ProductMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper productMapper;

    public ProductService(ProductRepository productRepository, ProductMapper productMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
    }

    public void addProduct(Product product) {
        productRepository.save(productMapper.map(product));
    }

    public Product getProduct(long id) throws ProductNotFoundException {
        Optional<ProductEntity> productById = productRepository.findById(id);
        ProductEntity productEntity = productById.orElseThrow(
                () -> new ProductNotFoundException("Failed to get product: " + id));
        return productMapper.map(productEntity);
    }

    public List<Product> getAllProducts() {
        List<ProductEntity> entityProducts = productRepository.findAll();
        List<Product> products = new ArrayList<>();
        for (ProductEntity product : entityProducts) {
            products.add(productMapper.map(product));
        }
        return products;
    }

    public Product updateProduct(Product product, long id) throws ProductNotFoundException {
        Optional<ProductEntity> optionalProduct = productRepository.findById(id);
        ProductEntity productEntity = optionalProduct.orElseThrow(() -> new ProductNotFoundException("Failed to return id."));
        Product updateProduct = productMapper.map(productEntity);
        updateProduct.setId(product.getId());
        updateProduct.setProduct(product.getProduct());
        updateProduct.setCost(product.getCost());
        return updateProduct;
    }

    public void deleteProduct(long id) {
        productRepository.deleteById(id);
    }
}
