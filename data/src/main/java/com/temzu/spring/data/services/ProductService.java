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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {
    private ProductRepository productRepository;

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDto> getAllProducts(
            int page,
            int size,
            String[] sortFields,
            String sortDir
    ) {
        Sort sort = Sort.by(sortFields);
        Pageable pageable = PageRequest.of(page - 1, size,
                sortDir.equals(SortDirection.ASC.toString()) ? sort.ascending() : sort.descending());

        Page<Product> products = productRepository.findAll(pageable);
        int totalPages = products.getTotalPages();
        if (totalPages < page)
            throw new PageDeterminationException(
                    String.format("Page not found: page %d, size %d, total pages %d", page, size, totalPages));

        return ProductMapper.MAPPER.toProductDtoList(products.toList());
    }

    public List<ProductDto> getAllProductByPriceLessThan(Integer param) {
        return ProductMapper.MAPPER.toProductDtoList(productRepository.findAllByPriceLessThan(param));
    }

    public List<ProductDto> getAllProductByPriceGreaterThan(Integer param) {
        return ProductMapper.MAPPER.toProductDtoList(productRepository.findAllByPriceGreaterThan(param));
    }

    public List<ProductDto> getAllProductsByPriceBetween(Integer first, Integer second) {
        return ProductMapper.MAPPER.toProductDtoList(productRepository.findAllByPriceBetween(first, second));
    }

    public List<ProductDto> getAllProductsByTitleContaining(String title) {
        return ProductMapper.MAPPER.toProductDtoList(productRepository.findByTitleContainingIgnoreCase(title));
    }

    public ProductDto getProductById(@PathVariable Long id) {
        return ProductMapper.MAPPER.productToProductDto(productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product with id: " + id + " doesn't exist")));
    }

    public ProductDto saveOrUpdate(@RequestBody ProductDto productDto) {
        Product product = ProductMapper.MAPPER.productDtoToProduct(productDto);
        return ProductMapper.MAPPER.productToProductDto(productRepository.save(product));
    }

    public void deleteProductById(@PathVariable Long id) {
        productRepository.deleteById(id);
    }
}
