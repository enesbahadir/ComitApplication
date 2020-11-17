package com.comit.controller;

import com.comit.model.Order;
import com.comit.model.OrderForm;
import com.comit.model.OrderProduct;
import com.comit.service.OrderProductService;
import com.comit.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    private final OrderProductService orderProductService;

    public OrderController(OrderService orderService, OrderProductService orderProductService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    @PostMapping("/api/orders")
    public Order createUser(@RequestBody OrderForm orderForm)
    {
        Order order = new Order();
        order.setOrderDate(orderForm.getLocalDate());
        order.setUser(orderForm.getUser());
        order = orderService.createOrder(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        Order finalOrder = order;
        orderForm.getProducts().forEach(product-> {
            orderProducts.add(
                    new OrderProduct(finalOrder, product)
            );
        });

        orderProducts.forEach(orderProductService::createOrderProduct);

        order.setProducts(orderProducts);
        orderService.update(order);
        return order;
    }

    @GetMapping("/api/orders")
    public Iterable<Order> getOrders() {
        return orderService.getOrders();
    }

    @GetMapping("/api/orders/{id}")
    public Order getOrder(@PathVariable Integer id )
    {
        return orderService.getOrderById(id);
    }

    @GetMapping("/api/orders/user/{id}")
    public List<Order> getOrdersByUser(@PathVariable Integer id ){
        return orderService.getOrdersByUser(id);
    }
}
