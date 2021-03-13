package com.temzu.spring.data.repositories;

import com.temzu.spring.data.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    List<Product> findAllByPriceLessThan(Integer param);

    List<Product> findAllByPriceGreaterThan(Integer param);

    List<Product> findAllByPriceBetween(Integer first, Integer second);

    List<Product> findByTitleContainingIgnoreCase(String title);
}
