package com.comit.execption;

public class OrderNorFoundException extends RuntimeException {
    public OrderNorFoundException(int id) {
        super("Could not find order " + id);
    }

    public OrderNorFoundException(String message) {
        super(message);
    }
}
