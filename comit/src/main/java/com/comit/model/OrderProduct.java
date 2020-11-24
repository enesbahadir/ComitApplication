package com.comit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderProduct {

    @EmbeddedId
    @JsonIgnore
    private OrderProductPk pk;

    public OrderProduct() {
        super();
    }

    public OrderProduct(Order order, Product product) {
        pk = new OrderProductPk();
        pk.setOrder(order);
        pk.setProduct(product);
    }

    @Transient
    public Product getProduct() {
        return this.pk.getProduct();
    }


    public OrderProductPk getPk() {
        return pk;
    }

    public void setPk(OrderProductPk pk) {
        this.pk = pk;
    }

}
