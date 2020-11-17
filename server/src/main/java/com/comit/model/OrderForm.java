package com.comit.model;

import java.time.LocalDate;
import java.util.List;

public class OrderForm {
    public OrderForm() {
    }

    public String getLocalDate() {
        return localDate;
    }

    public void setLocalDate(String localDate) {
        this.localDate = localDate;
    }

    public OrderForm(List<Product> products, User user, String localDate) {
        this.products = products;
        this.user = user;
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

    private List<Product> products;

    private User user;

    private String localDate;

}
