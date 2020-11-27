package com.comit.service;

import com.comit.execption.OrderNorFoundException;
import com.comit.model.Order;
import com.comit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Sistemde kayıtlı Order sınıfının CRUD işlemlerinin gerçekleştirildiği service sınıfıdır.
 */
@Service
public class OrderService {

    @Autowired
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    /**
     * Order nesnesi oluşturmak için kullanılan metod. Order nesnesi üzerinden yeni Product'ı repository'e kayıt eder.
     * @param model Eklenecek olan Order nesnesi
     * @return Eklenen Order nesnesi
     */
    public Order createOrder(Order model)
    {
        return orderRepository.saveAndFlush(model);
    }

    /**
     * Kayıtlı order nesnesini güncellemek için kullanılan metod
     * @param order Güncellenecek olan order nesnesi
     */
    public void update(Order order) {
        orderRepository.save(order);
    }

    /**
     * Verilen id değeri ile bu id değerine sahip Order nesnesi döner
     * @param id Aranan id değeri
     * @return id değerine sahip Order nesnesi
     */
    public Order getOrderById(int id)
    {
        return orderRepository.findById(id).
                orElseThrow( () -> new OrderNorFoundException(id));
    }

    /**
     * Sistemde kayıtlı olan tüm Order nesnelerini listeleyen metod
     * @return ayıtlı Order'ların olduğu liste
     */
    public List<Order> getOrders()
    {
        return orderRepository.findAll();
    }

    /**
     * Verilen User id değerine göre, ilgili User'a ait olan Order nesnelerinin listelendiği metod
     * @param id User id değeri
     * @return ilgili user'a ait Order nesneleri listesi
     */
    public List<Order> getOrdersByUser(int id) {
        return orderRepository.getOrdersByUserId(id);
    }

}
