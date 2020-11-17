package com.comit.service;

import com.comit.model.Order;
import com.comit.model.OrderProduct;
import com.comit.model.Product;
import com.comit.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderProductService {

    @Autowired
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    public OrderProduct createOrderProduct(OrderProduct orderProduct)
    {
        return orderProductRepository.save(orderProduct);
    }
}
