package com.comit.execption;

/**
 * Sistemde kayıtlı ilgili Product nesnesini bulunamayınca fırlatılan exception
 */
public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(Integer id) {
        super(id + " numaralı ürün bulunamadı!");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
