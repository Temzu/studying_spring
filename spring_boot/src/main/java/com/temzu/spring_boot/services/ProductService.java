package com.temzu.spring_boot.services;

import com.temzu.spring_boot.model.Product;
import com.temzu.spring_boot.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public Optional<Product> getProductById(Long id) {
        return productRepository.getProductById(id);
    }

    public void removeById(Long id) {
        productRepository.removeById(id);
    }

    public void saveOrUpdate(Product product) {
        productRepository.saveOrUpdate(product);
    }
}
