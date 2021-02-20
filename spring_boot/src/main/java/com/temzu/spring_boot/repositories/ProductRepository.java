package com.temzu.spring_boot.repositories;

import com.temzu.spring_boot.model.Product;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ProductRepository {
    private List<Product> products;

    @PostConstruct
    public void init() {
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

    public List<Product> getAllProducts() {
        return Collections.unmodifiableList(products);
    }

    public Optional<Product> getProductById(Long id) {
        return products.stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public void removeById(Long id) {
        products.removeIf(p -> p.getId().equals(id));
    }

    public void saveOrUpdate(Product product) {
        products.stream()
                .filter(p -> p.getId().equals(product.getId()))
                .findFirst()
                .ifPresentOrElse(
                        (p) -> {
                            products.set(products.indexOf(p), product);
                        },
                        () -> {
                            Long id = products.stream().mapToLong(Product::getId).max().orElse(0L) + 1L;
                            product.setId(id);
                            products.add(product);
                        });
    }
}
