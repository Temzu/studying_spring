package com.temzu.spring.data.controller;

import com.temzu.spring.data.model.Product;
import com.temzu.spring.data.model.SortDirection;
import com.temzu.spring.data.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping
    public List<Product> getAllProducts(
            @RequestParam(defaultValue = "1", required = false) Integer page,
            @RequestParam(defaultValue = "5", required = false) Integer size,
            @RequestParam(defaultValue = "id", required = false) String[] sortFields,
            @RequestParam(defaultValue = "ASC", required = false) SortDirection sortDir
            ) {
        return productService.getAllProducts(page, size, sortFields, sortDir.toString()).toList();
    }

    @GetMapping("/less")
    public List<Product> getProductsByPriceLessThan(@RequestParam Integer param) {
        return productService.getAllProductByPriceLessThan(param);
    }

    @GetMapping("/greater")
    public List<Product> getProductsByPriceGreaterThan(@RequestParam Integer param) {
        return productService.getAllProductByPriceGreaterThan(param);
    }

    @GetMapping("/between")
    public List<Product> getAllProductsByPriceBetween(@RequestParam Integer first, @RequestParam Integer second) {
        return productService.getAllProductsByPriceBetween(first, second);
    }

    @GetMapping("/contain")
    public List<Product> getAllProductsByTitleContaining(@RequestParam String s) {
        return productService.getAllProductsByTitleContaining(s);
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return productService.addProduct(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProductById(@PathVariable Long id) {
        productService.deleteProductById(id);
    }


}
