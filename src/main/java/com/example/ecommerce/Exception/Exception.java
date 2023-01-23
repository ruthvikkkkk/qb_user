package com.example.ecommerce.Exception;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
public class Exception extends RuntimeException {

//    private static final long serialVersionUID = 1 L;

    public Exception(String message) {
        super(message);
    }

    public Exception(String message, Throwable throwable) {
        super(message, throwable);
    }
}