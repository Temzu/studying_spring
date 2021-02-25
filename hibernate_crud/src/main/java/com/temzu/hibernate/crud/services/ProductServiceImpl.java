package com.temzu.hibernate.crud.services;

import com.temzu.hibernate.crud.dao.ProductDao;
import com.temzu.hibernate.crud.dao.ProductDaoImpl;
import com.temzu.hibernate.crud.model.Product;

import java.util.List;

public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao = new ProductDaoImpl();

    @Override
    public void saveOrUpdateProduct(Product p) {
        productDao.saveOrUpdateProduct(p);
    }

    @Override
    public List<Product> listProducts() {
        return productDao.listProducts();
    }

    @Override
    public Product getProductById(Long id) {
        return productDao.getProductById(id);
    }

    @Override
    public void deleteProduct(Long id) {
        productDao.deleteProduct(id);
    }
}
