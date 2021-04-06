package com.temzu.hibernate.crud.services;

import com.temzu.hibernate.crud.model.Product;

import java.util.List;

public interface ProductService {
    void saveOrUpdateProduct(Product p);
    List<Product> listProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
}
