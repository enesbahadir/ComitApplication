package com.comit.payload;

/**
 * Kullanıcnın register işlemi sonrası dönen response mesaj bilgisinin tutulduğu sınıftır.
 */
public class MessageResponse {

    private String message;

    public MessageResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
