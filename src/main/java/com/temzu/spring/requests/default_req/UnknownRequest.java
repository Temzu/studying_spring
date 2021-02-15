package com.temzu.spring.requests.default_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class UnknownRequest implements Request {

    @Override
    public void processing(ProductService productService, String[] req) {
        System.out.println("Unknown command: " + req[0]);
        info();
    }
}
