package com.comit.execption;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException(Integer id) {
        super("Could not find product " + id);
    }
    public ProductNotFoundException(String message) {
        super(message);
    }
}
