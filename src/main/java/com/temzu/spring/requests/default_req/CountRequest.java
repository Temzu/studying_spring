package com.temzu.spring.requests.default_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class CountRequest implements Request {

    @Override
    public void processing(ProductService productService, String[] req) {
        System.out.println("number of products: " + productService.getNumberProducts());
    }
}
