package com.temzu.spring.repositories;

import com.temzu.spring.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> getProductsList();
    void addProduct(Product product);
    Product getProductById(Integer id);
    Product updateProduct(Integer id, String title, Integer cost);
    Product deleteProductById(Product product);
}
