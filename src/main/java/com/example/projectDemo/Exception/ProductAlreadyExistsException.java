package com.example.projectDemo.Exception;

public class ProductAlreadyExistsException extends RuntimeException{

    private String message;

    public ProductAlreadyExistsException() {}

    public ProductAlreadyExistsException(String msg) {
        super(msg);
        this.message = msg;
    }
}
