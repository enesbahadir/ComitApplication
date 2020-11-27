package com.comit.execption;

/**
 * Sistemde kayıtlı ilgili Order nesnesini bulunamayınca fırlatılan exception
 */
public class OrderNorFoundException extends RuntimeException {

    public OrderNorFoundException(int id) {
        super(id + " numaralı sipariş bulunamadı!");
    }

    public OrderNorFoundException(String message) {
        super(message);
    }
}
