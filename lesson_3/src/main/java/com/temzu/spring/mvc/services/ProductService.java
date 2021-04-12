package com.temzu.spring.mvc.services;

import com.temzu.spring.mvc.model.Product;
import com.temzu.spring.mvc.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProductById(Long id) {
        return productRepository.getProducts().get(id.intValue() - 1);
    }

    public List<Product> getAllProducts() {
        return productRepository.getProducts();
    }

    public void deleteProductById(Long id) {
        productRepository.removeById(id);
    }

    public void save(Product product) {
        productRepository.save(product);
    }
}
