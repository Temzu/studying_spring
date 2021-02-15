package com.temzu.spring.requests.default_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class HelpRequest implements Request {

    @Override
    public void processing(ProductService productService, String[] req) {
        info();
    }
}
