package com.temzu.spring_boot.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Long id) {
        super("Product not found!: id " + id);
    }
}
