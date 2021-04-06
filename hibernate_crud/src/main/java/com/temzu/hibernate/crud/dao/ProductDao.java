package com.temzu.hibernate.crud.dao;

import com.temzu.hibernate.crud.model.Product;

import java.util.List;

public interface ProductDao {
    void saveOrUpdateProduct(Product product);
    List<Product> listProducts();
    Product getProductById(Long id);
    void deleteProduct(Long id);
}
