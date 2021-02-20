package com.temzu.spring_boot.exceptions;

import com.temzu.spring_boot.exceptions.ProductNotFoundException;
import com.temzu.spring_boot.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Response> handleProductException(ProductNotFoundException e, HttpServletRequest request) {
        Response response = Response.builder()
                .timestamp(LocalDateTime.now())
                .message(e.getMessage())
                .status(HttpStatus.NOT_FOUND.value())
                .path(request.getServletPath())
                .build();
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
