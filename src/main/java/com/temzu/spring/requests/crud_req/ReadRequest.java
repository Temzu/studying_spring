package com.temzu.spring.requests.crud_req;

import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;

public class ReadRequest implements Request {
    @Override
    public void processing(ProductService productService, String[] req) {
        if (req.length < 2 || req.length > 3) {
            System.out.println("Error read request");
            info();
            return;
        }
        try {
            if (req[1].equals("list"))
                productService.getProducts().forEach(System.out::println);
            else if (req[1].equals("id"))
                System.out.println(productService.getProductById(Integer.parseInt(req[2])));
        } catch (NumberFormatException e) {
            System.out.printf("Error read request: id = %s", req[2]);
            info();
        }
    }
}
