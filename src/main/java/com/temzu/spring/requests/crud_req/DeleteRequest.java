package com.temzu.spring.requests.crud_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class DeleteRequest implements Request {
    @Override
    public void processing(ProductService productService, String[] req) {
        if (req.length != 2) {
            System.out.println("Error delete request");
            info();
            return;
        }
        try {
            int id = Integer.parseInt(req[1]);
            System.out.println("Product deleted: " + productService.deleteProductById(id));
        } catch (NumberFormatException e) {
            System.out.printf("Error delete request: id = %s \n", req[1]);
            info();
        }
    }
}
