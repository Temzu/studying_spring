package com.temzu.spring.requests;

import com.temzu.spring.command.CommandRequest;
import com.temzu.spring.service.ProductService;

public interface Request {
    void processing(ProductService productService, String[] req);

    default void info() {
        System.out.printf(
                "Commands:\n" +
                        "%-35s - view all products\n" +
                        "%-35s - view product by id\n" +
                        "%-35s - create new product\n" +
                        "%-35s - change product title and cost\n" +
                        "%-35s - delete product by id\n" +
                        "%-35s - number of products\n" +
                        "%-35s - Avg of cost\n" +
                        "%-35s - Stop the program\n",
                CommandRequest.GET + " all",
                CommandRequest.GET + " id [id]",
                CommandRequest.CREATE + " [title] [cost]",
                CommandRequest.UPDATE + " [id] [new title] [new cost]",
                CommandRequest.DELETE + " [id]",
                CommandRequest.COUNT,
                CommandRequest.AVG,
                CommandRequest.STOP);
    }
}
