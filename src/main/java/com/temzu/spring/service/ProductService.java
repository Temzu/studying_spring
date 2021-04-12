package com.temzu.spring.service;

import com.temzu.spring.model.Product;
import com.temzu.spring.repositories.IProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class ProductService {

    private IProductRepository productRepository;
    private Integer prodId;

    @Autowired
    @Qualifier("productRepository")
    public void setProductRepository(IProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @PostConstruct
    public void init() {
        prodId = getProducts().size();
    }

    public List<Product> getProducts() {
        return productRepository.getProductsList();
    }

    public Product createNewProduct(String title, int cost) {
        Product product = new Product(++prodId, title, cost);
        productRepository.addProduct(product);
        return product;
    }

    public Product getProductById(Integer id) {
        return productRepository.getProductById(id);
    }

    public Product updateProduct(int id, String title, int cost) {
        return productRepository.updateProduct(id, title, cost);
    }

    public Product deleteProductById(Integer id) {
        Product product = getProducts().stream()
                .filter(p -> p.getId().equals(id))
                .findAny()
                .orElse(null);
        return productRepository.deleteProductById(product);
    }

    public Integer getNumberProducts() {
        return getProducts().size();
    }

    public Double getCostAvg() {
        return getProducts().stream()
                .mapToInt(Product::getCost)
                .average()
                .orElse(0);
    }
}
