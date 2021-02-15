package com.temzu.spring.requests.default_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class StopRequest implements Request {

    @Override
    public void processing(ProductService productService, String[] req) {
    }
}
