package com.temzu.spring.mvc.repositories;

import com.temzu.spring.mvc.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.ServletException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() throws ServletException {
        products = new ArrayList<>();
        products.add(new Product(1L, "Milk", 50));
        products.add(new Product(2L, "Meat", 200));
        products.add(new Product(3L, "Watter", 30));
        products.add(new Product(4L, "Fish", 1500));
        products.add(new Product(5L, "Egg", 62));
        products.add(new Product(6L, "Tea", 120));
        products.add(new Product(7L, "Rice", 73));
        products.add(new Product(8L, "Cabbage", 22));
        products.add(new Product(9L, "Apple", 100));
        products.add(new Product(10L, "Orange", 90));
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void removeById(Long id) {
        products.removeIf(product -> product.getId().equals(id));
    }

    public void save(Product product) {
        products.add(product);
    }
}
