package com.temzu.spring.data.controller;

import com.temzu.spring.data.model.SortDirection;
import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.repositories.specifications.ProductSpecifications;
import com.temzu.spring.data.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public Page<ProductDto> getAllProducts(
            @RequestParam MultiValueMap<String, String> params,
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String[] sortFields,
            @RequestParam(defaultValue = "ASC", required = false) SortDirection sortDir
    ) {
        if (page < 0 || size <= 0) {
            page = 1;
            size = 5;
        }
        return productService.getAllProducts(ProductSpecifications.build(params), page, size, sortFields, sortDir.toString());
    }

    @GetMapping("/{id}")
    public ProductDto getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto saveProduct(@RequestBody ProductDto productDto) {
        productDto.setId(null);
        return productService.saveOrUpdate(productDto);
    }

    @PutMapping
    public ProductDto updateProduct(@RequestBody ProductDto productDto) {
        return productService.saveOrUpdate(productDto);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }

}
