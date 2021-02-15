package com.temzu.spring.requests.crud_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class CreateRequest implements Request {
    @Override
    public void processing(ProductService productService, String[] req) {
        if (req.length != 3) {
            System.out.println("Error: It is required to enter the name and price of the product");
            info();
            return;
        }
        try {
            int cost = Integer.parseInt(req[2]);
            String title = req[1];
            System.out.println("Product created: " + productService.createNewProduct(title, cost));
        } catch (NumberFormatException e) {
            System.out.printf("Error create request: title = %s cost = %s\n", req[1], req[2]);
            info();
        }
    }
}
