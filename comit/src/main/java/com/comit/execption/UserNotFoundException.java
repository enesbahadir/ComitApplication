package com.comit.execption;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("Could not found user "+ id);
    }

    public UserNotFoundException() {
        super("Could not found user");
    }
}
