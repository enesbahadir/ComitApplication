package com.comit.payload;

import com.comit.model.Product;
import com.comit.model.User;

import java.util.List;

/**
 * Order oluşturmak için kullanıcının post etmesi icap eden bilgilerin tutulduğu sınıftır.
 */
public class OrderForm {

    private List<Product> products;

    private User user;

    private String localDate;

    public OrderForm() {
    }

    public OrderForm(List<Product> products, User user, String localDate) {
        this.products = products;
        this.user = user;
        this.localDate = localDate;
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

}
