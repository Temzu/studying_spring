package com.temzu.spring.repositories;

import com.temzu.spring.model.Product;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
        products = new ArrayList<>();
        products.add(new Product(1, "Milk", 50));
        products.add(new Product(2, "Meat", 200));
        products.add(new Product(3, "Watter", 30));
        products.add(new Product(4, "Fish", 1500));
        products.add(new Product(5, "Egg", 62));
        products.add(new Product(6, "Tea", 120));
        products.add(new Product(7, "Rice", 73));
        products.add(new Product(8, "Cabbage", 22));
        products.add(new Product(9, "Apple", 100));
        products.add(new Product(10, "Orange", 90));
    }

    public List<Product> getProductsList() {
        return Collections.unmodifiableList(products);
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public Product getProductById(Integer id) {
        return products.stream()
                .filter(product -> product.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Product updateProduct(Integer id, String title, Integer cost) {
        Product product = getProductById(id);
        if (product == null)
            return null;
        product.setTitle(title);
        product.setCost(cost);
        return product;
    }

    public Product deleteProductById(Product product) {
        if (product == null)
            return null;
        products.remove(product);
        return product;
    }
}
