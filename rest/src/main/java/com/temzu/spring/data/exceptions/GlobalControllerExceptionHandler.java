package com.temzu.spring.data.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ExceptionHandler(EnumConvertException.class)
    public ResponseEntity<?> handleEnumConvert(RuntimeException ex) {
        MarketError err = new MarketError(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(err, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({PageDeterminationException.class, ProductNotFoundException.class})
    public ResponseEntity<?> handlePaginationError(RuntimeException ex) {
        MarketError err = new MarketError(HttpStatus.NOT_FOUND.value(), ex.getMessage());
        System.out.println(err);
        return new ResponseEntity<>(err, HttpStatus.NOT_FOUND);
    }
}
