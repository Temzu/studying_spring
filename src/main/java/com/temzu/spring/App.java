package com.temzu.spring;

import com.temzu.spring.command.CommandRequest;
import com.temzu.spring.config.AppConfig;
import com.temzu.spring.annotations.Req;
import com.temzu.spring.controllers.ProductController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class App {

    public static void main(String[] args) throws IOException, InvocationTargetException, IllegalAccessException {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        ProductController productController = applicationContext.getBean("productController", ProductController.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Method unknownMethod = null;
        Method[] allMethods = productController.getClass().getDeclaredMethods();
        List<Method> annotatedMethods = new ArrayList<>();

        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Req.class)) {
                if (method.getAnnotation(Req.class).value().equals(CommandRequest.UNKNOWN.getQualifier())) {
                    unknownMethod = method;
                }
                annotatedMethods.add(method);
            }
        }

        CommandRequest command = CommandRequest.UNKNOWN;
        while (command != CommandRequest.STOP) {
            String[] req = reader.readLine().split(" ");
            command = CommandRequest.defineCommand(req[0]);
            CommandRequest finalCommand = command;
            Objects.requireNonNull(annotatedMethods.stream()
                    .filter(method -> method.getAnnotation(Req.class).value().equals(finalCommand.getQualifier()))
                    .findAny()
                    .orElse(unknownMethod))
                    .invoke(productController, (Object) req);
        }
    }
}
