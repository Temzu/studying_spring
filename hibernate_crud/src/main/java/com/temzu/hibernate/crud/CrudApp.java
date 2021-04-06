package com.temzu.hibernate.crud;

import com.temzu.hibernate.crud.model.Product;
import com.temzu.hibernate.crud.services.ProductService;
import com.temzu.hibernate.crud.services.ProductServiceImpl;
import com.temzu.hibernate.crud.util.HibernateUtil;

public class CrudApp {

    public static void main(String[] args) {
        ProductService productService = new ProductServiceImpl();

        try {
            Product product = new Product(1L, "milk", 342);
            productService.saveOrUpdateProduct(product);
            System.out.println(productService.getProductById(3L));
            System.out.println(productService.listProducts());
            productService.deleteProduct(3L);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            HibernateUtil.shutdown();
        }
    }
}
