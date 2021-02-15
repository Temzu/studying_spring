package com.temzu.spring;

import com.temzu.spring.command.CommandRequest;
import com.temzu.spring.config.AppConfig;
import com.temzu.spring.requests.Request;
import com.temzu.spring.service.ProductService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App {

    public static void main(String[] args) throws IOException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductService productService = applicationContext.getBean("productService", ProductService.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CommandRequest command = CommandRequest.HELP;
        command.getRequest().info();
        while (command != CommandRequest.STOP) {
            String[] req = reader.readLine().split(" ");
            command = CommandRequest.defineCommand(req[0]);
            Request request = command.getRequest();
            request.processing(productService, req);
        }
    }

}
