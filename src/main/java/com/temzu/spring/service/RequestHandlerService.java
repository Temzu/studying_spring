package com.temzu.spring.service;

import com.temzu.spring.annotations.Req;
import com.temzu.spring.command.CommandsReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RequestHandlerService {

    private ProductService productService;

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Req(value = CommandsReq.GET)
    public void getProducts(String[] req) {
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

    @Req(value = CommandsReq.CREATE)
    public void createNewProduct(String[] req) {
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

    @Req(value = CommandsReq.UPDATE)
    public void updateProduct(String[] req) {
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

    @Req(value = CommandsReq.DELETE)
    public void deleteProductById(String[] req) {
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

    @Req(value = CommandsReq.COUNT)
    public void getNumberProducts(String[] req) {
        if (req.length > 1) {
            System.out.println("Error count request");
            info();
            return;
        }
        System.out.println("number of products: " + productService.getNumberProducts());
    }

    @Req(value = CommandsReq.AVG)
    public void getCostAvg(String[] req) {
        if (req.length > 1) {
            System.out.println("Error avg request");
            info();
            return;
        }
        System.out.println("Avg of cost: " + productService.getCostAvg());
    }

    @Req(value = CommandsReq.HELP)
    public void help(String[] req) {
        if (req.length > 1) {
            System.out.println("Error help request");
            info();
            return;
        }
        info();
    }

    @Req(value = CommandsReq.STOP)
    public void stop(String[] req) {
        if (req.length > 1) {
            System.out.println("Error stop request");
            info();
            return;
        }
        System.out.println("App stopped!");
    }

    @Req(value = CommandsReq.UNKNOWN)
    public void unknown(String[] req) {
        System.out.println("Unknown command: " + req[0]);
        info();
    }

    private void info() {
        System.out.printf(
                "CommandsReq:\n" +
                        "%-35s - view all products\n" +
                        "%-35s - view product by id\n" +
                        "%-35s - create new product\n" +
                        "%-35s - change product title and cost\n" +
                        "%-35s - delete product by id\n" +
                        "%-35s - number of products\n" +
                        "%-35s - Avg of cost\n" +
                        "%-35s - Stop the program\n",
                CommandsReq.GET + " list",
                CommandsReq.GET + " id [id]",
                CommandsReq.CREATE + " [title] [cost]",
                CommandsReq.UPDATE + " [id] [new title] [new cost]",
                CommandsReq.DELETE + " [id]",
                CommandsReq.COUNT,
                CommandsReq.AVG,
                CommandsReq.STOP);
    }
}
