package com.temzu.spring.data.services;

import com.temzu.spring.data.exceptions.PageDeterminationException;
import com.temzu.spring.data.model.Product;
import com.temzu.spring.data.model.SortDirection;
import com.temzu.spring.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Page<Product> getAllProducts(int page, int size, String[] sortFields, String sortDir) {
        if (page <= 0 || size <= 0)
            throw new PageDeterminationException(
                    String.format("Page or size less or equal to zero: page %d, size %d", page, size));

        Sort sort = Sort.by(sortFields);
        Pageable pageable = PageRequest.of(page - 1, size,
                sortDir.equals(SortDirection.ASC.toString()) ? sort.ascending() : sort.descending());

        Page<Product> products = productRepository.findAll(pageable);
        int totalPages = products.getTotalPages();
        if (totalPages <= page)
            throw new PageDeterminationException(
                    String.format("Page not found: page %d, size %d, total pages %d", page, size, totalPages));

        return products;
    }

    public List<Product> getAllProductByPriceLessThan(Integer param) {
        return productRepository.findAllByPriceLessThan(param);
    }

    public List<Product> getAllProductByPriceGreaterThan(Integer param) {
        return productRepository.findAllByPriceGreaterThan(param);
    }

    public List<Product> getAllProductsByPriceBetween(Integer first, Integer second) {
        return productRepository.findAllByPriceBetween(first, second);
    }

    public List<Product> getAllProductsByTitleContaining(String title) {
        return productRepository.findByTitleContainingIgnoreCase(title);
    }

    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public Product addProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
