package com.comit.controller;

import com.comit.model.Order;
import com.comit.payload.OrderForm;
import com.comit.model.OrderProduct;
import com.comit.service.OrderProductService;
import com.comit.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Order-Sipariş işlemlerini gerçekleştiren api controller
 */
@RestController
public class OrderController {

    private final OrderService orderService;

    private final OrderProductService orderProductService;

    public OrderController(OrderService orderService, OrderProductService orderProductService) {
        this.orderService = orderService;
        this.orderProductService = orderProductService;
    }

    /**
     * Yeni bir order nesnesi oluşturmak için kullanılan HTTP-POST metodu
     *
     * @param orderForm order oluşturmak için post edilen OrderFrom nesnesi. OrderForm nesnesi id içermez.
     * @return Order nesnesi
     */
    @PostMapping("/api/orders")
    public Order createOrder(@RequestBody OrderForm orderForm) {
        Order order = new Order();
        order.setOrderDate(orderForm.getLocalDate());
        order.setUser(orderForm.getUser());
        order = orderService.createOrder(order);

        List<OrderProduct> orderProducts = new ArrayList<>();
        Order finalOrder = order;
        orderForm.getProducts().forEach(product -> {
            orderProducts.add(
                    new OrderProduct(finalOrder, product)
            );
        });

        orderProducts.forEach(orderProductService::createOrderProduct);

        order.setProducts(orderProducts);
        orderService.update(order);
        return order;
    }

    /**
     * Sistemde kayıtlı order nesnelerinin listelerini dönen HTTP-GET metodu
     * @return Order nesne listesi
     */
    @GetMapping("/api/orders")
    public Iterable<Order> getOrders() {
        return orderService.getOrders();
    }

    /**
     * İlgili id'ye ait olan Order nesnesini dönen HTTP-GET metodu
     * @param id ilgili order nesnesinin id'si
     * @return ilgili order nesnesi
     */
    @GetMapping("/api/orders/{id}")
    public Order getOrder(@PathVariable Integer id) {
        return orderService.getOrderById(id);
    }

    /**
     * Sistemde kayıtlı olan User'a iat olan order nesnelerinin oluşturduğu listeyi dönen HTTP-GET metodu
     * @param id User id'si
     * @return Order listesi
     */
    @GetMapping("/api/orders/user/{id}")
    public List<Order> getOrdersByUser(@PathVariable Integer id) {
        return orderService.getOrdersByUser(id);
    }
}
