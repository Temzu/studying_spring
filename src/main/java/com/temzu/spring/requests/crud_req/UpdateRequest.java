package com.temzu.spring.requests.crud_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class UpdateRequest implements Request {
    @Override
    public void processing(ProductService productService, String[] req) {
        if (req.length != 4) {
            System.out.println("Error update request");
            info();
            return;
        }
        try {
            int id = Integer.parseInt(req[1]);
            int cost = Integer.parseInt(req[3]);
            String title = req[2];
            System.out.println("Product updated to: " + productService.updateProduct(id, title, cost));
        } catch (NumberFormatException e) {
            System.out.printf("Error create request: id = %s title = %s cost = %s\n", req[1], req[2], req[3]);
            info();
        }
    }
}
