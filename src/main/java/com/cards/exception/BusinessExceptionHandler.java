package com.cards.exception;

import lombok.Data;

@Data
public class BusinessExceptionHandler {

    private String message;

    public BusinessExceptionHandler(String message) {
        this.message = message;
    }

}
