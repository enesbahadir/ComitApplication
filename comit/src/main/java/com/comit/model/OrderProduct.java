package com.comit.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class OrderProduct {
/*
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Order order;

    public OrderProduct() {
    }

    public OrderProduct(Product product, Order order) {
        this.product = product;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
*/
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
