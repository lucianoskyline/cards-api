package com.cards.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionController {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity exception(MethodArgumentNotValidException ex) {
        var businessExceptionHandler = new BusinessExceptionHandler(ex.getFieldError().getDefaultMessage());
        return ResponseEntity.badRequest().body(businessExceptionHandler);
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BusinessExceptionHandler> runtimeException(RuntimeException ex) {
        var businessExceptionHandler = new BusinessExceptionHandler(ex.getMessage());
        return ResponseEntity.badRequest().body(businessExceptionHandler);
    }


}
