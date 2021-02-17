package com.temzu.spring;

import com.temzu.spring.command.CommandsReq;
import com.temzu.spring.config.AppConfig;
import com.temzu.spring.annotations.Req;
import com.temzu.spring.service.RequestHandlerService;
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
        RequestHandlerService requestHandlerService = applicationContext.getBean("requestHandlerService", RequestHandlerService.class);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Method unknownMethod = null;
        Method[] allMethods = requestHandlerService.getClass().getDeclaredMethods();
        List<Method> annotatedMethods = new ArrayList<>();

        for (Method method : allMethods) {
            if (method.isAnnotationPresent(Req.class)) {
                if (method.getAnnotation(Req.class).value().equals(CommandsReq.UNKNOWN)) {
                    unknownMethod = method;
                }
                annotatedMethods.add(method);
            }
        }

        System.out.println("Enter the command '/help' to see all commands...");
        String cmd = CommandsReq.UNKNOWN;
        while (!cmd.equals(CommandsReq.STOP)) {
            String[] reqParam = reader.readLine().split(" ");
            cmd = reqParam[0];
            Objects.requireNonNull(annotatedMethods.stream()
                    .filter(method -> method.getAnnotation(Req.class).value().equals(reqParam[0]))
                    .findAny()
                    .orElse(unknownMethod))
                    .invoke(requestHandlerService, (Object) reqParam);
        }
    }
}
