package com.temzu.spring.data.services;

import com.temzu.spring.data.model.Product;
import com.temzu.spring.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getAllProductByPriceLessThan(Integer param) {
        return productRepository.findAllByPriceLessThan(param);
    }

    public List<Product> getAllProductByPriceGreaterThan(Integer param) {
        return productRepository.findAllByPriceGreaterThan(param);
    }

    public List<Product> getAllProductsByPriceBetween(Integer first, Integer second) {
        return productRepository.findAllByPriceBetween(first, second);
    }

    public List<Product> getAllProductsByTitleContaining(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title);
    }

    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
