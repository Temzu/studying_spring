package com.temzu.spring.data.services;

import com.temzu.spring.data.model.dtos.ProductDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class CartService {
    private List<ProductDto> cart = new ArrayList<>();

    public List<ProductDto> getProductsFromCart() {
        return Collections.unmodifiableList(cart);
    }

    public List<ProductDto> addToCart(ProductDto productDto) {
        if (productDto != null) {
            cart.add(productDto);
        }
        return Collections.unmodifiableList(cart);
    }

    public List<ProductDto> deleteFromCart(Long id) {
        cart.removeIf(productDto -> productDto.getId().equals(id));
        return Collections.unmodifiableList(cart);
    }

    public List<ProductDto> deleteOneProductFromCart(ProductDto productDto) {
        cart.remove(productDto);
        return Collections.unmodifiableList(cart);
    }
}
