package com.temzu.spring.data.controller;

import com.temzu.spring.data.model.dtos.ProductDto;
import com.temzu.spring.data.services.CartService;
import com.temzu.spring.data.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private CartService cartService;

    @Autowired
    private ProductService productService;

    @GetMapping("/cart")
    public List<ProductDto> getProductsFromCart() {
        return cartService.getProductsFromCart();
    }

    @PutMapping("/cart/{id}")
    public List<ProductDto> incDecProduct(
            @RequestParam String c,
            @PathVariable Long id
    ) {
        if (c.equals("dec")) {
            return cartService.deleteOneProductFromCart(productService.getProductById(id));
        } else if (c.equals("inc")) {
            return cartService.addToCart(productService.getProductById(id));
        }
        return cartService.getProductsFromCart();
    }

    @DeleteMapping("/cart/{id}")
    public List<ProductDto> deleteProductFromCart(@PathVariable Long id) {
        return cartService.deleteFromCart(id);
    }
}
