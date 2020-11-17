package com.comit.service;

import com.comit.execption.OrderNorFoundException;
import com.comit.execption.ProductNotFoundException;
import com.comit.model.Order;
import com.comit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public Order createOrder(Order model)
    {
        return orderRepository.saveAndFlush(model);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public Order getOrderById(int id)
    {
        return orderRepository.findById(id).
                orElseThrow( () -> new OrderNorFoundException(id));
    }

    public List<Order> getOrders()
    {
        return orderRepository.findAll();
    }

    public List<Order> getOrdersByUser(int id) {
        return orderRepository.getOrdersByUserId(id);
    }

}
