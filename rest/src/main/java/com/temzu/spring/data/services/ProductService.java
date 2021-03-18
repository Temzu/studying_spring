package com.temzu.spring.data.services;

import com.temzu.spring.data.exceptions.PageDeterminationException;
import com.temzu.spring.data.exceptions.ProductNotFoundException;
import com.temzu.spring.data.model.SortDirection;
import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.model.entities.Product;
import com.temzu.spring.data.model.mappers.ProductMapper;
import com.temzu.spring.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private ProductRepository productRepository;
    private ProductMapper mapper;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Autowired
    public void setMapper(ProductMapper mapper) {
        this.mapper = mapper;
    }

    public Page<ProductDto> getAllProducts(
            Specification<Product> spec,
            int page,
            int size,
            String[] sortFields,
            String sortDir
    ) {

        Sort sort = Sort.by(sortFields);
        Pageable pageable = PageRequest.of(
                page - 1, size, sortDir.equals(SortDirection.ASC.toString()) ? sort.ascending() : sort.descending()
        );
        Page<ProductDto> products = productRepository.findAll(spec, pageable).map(mapper::toProductDto);
        int totalPages = products.getTotalPages();
        if (totalPages < page) {
            throw new PageDeterminationException(
                    String.format("Page not found: page %d, size %d, total pages %d", page, size, totalPages));
        }
        return products;
    }

    public ProductDto getProductById(@PathVariable Long id) {
        return mapper.toProductDto(productRepository
                .findById(id).orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " doesn't exist")));
    }

    public ProductDto saveOrUpdate(@RequestBody ProductDto productDto) {
        Product product = mapper.toProduct(productDto);
        return mapper.toProductDto(productRepository.save(product));
    }

    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
