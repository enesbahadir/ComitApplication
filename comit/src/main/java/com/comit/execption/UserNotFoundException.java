package com.comit.execption;

/**
 * Sistemde kayıtlı ilgili User nesnesini bulunamayınca fırlatılan exception
 */
public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException(Integer id) {
        super(id + " numaralı kullanıcı bulunamadı!");
    }

    public UserNotFoundException() {
        super("Could not found user");
    }
}
