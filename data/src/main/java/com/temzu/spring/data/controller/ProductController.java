package com.temzu.spring.data.controller;

import com.temzu.spring.data.model.SortDirection;
import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<ProductDto> getAllProducts(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String[] sortFields,
            @RequestParam(defaultValue = "ASC", required = false) SortDirection sortDir
    ) {
        if (page < 0 || size <= 0) {
            page = 1;
            size = 5;
        }
        return productService.getAllProducts(page, size, sortFields, sortDir.toString());
    }

    @GetMapping("/less")
    public List<ProductDto> getProductsByPriceLessThan(@RequestParam Integer param) {
        return productService.getAllProductByPriceLessThan(param);
    }

    @GetMapping("/greater")
    public List<ProductDto> getProductsByPriceGreaterThan(@RequestParam Integer param) {
        return productService.getAllProductByPriceGreaterThan(param);
    }

    @GetMapping("/between")
    public List<ProductDto> getProductsByPriceBetween(@RequestParam Integer first, @RequestParam Integer second) {
        return productService.getAllProductsByPriceBetween(first, second);
    }

    @GetMapping("/contain")
    public List<ProductDto> getProductsByTitleContaining(@RequestParam String s) {
        return productService.getAllProductsByTitleContaining(s);
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
