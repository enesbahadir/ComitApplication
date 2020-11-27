package com.comit.service;

import com.comit.model.OrderProduct;
import com.comit.repository.OrderProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Sistemde kayıtlı olan OrderProduct nesneleri CRUD işlemleri için kullanılan sınıftır.
 */
@Service
public class OrderProductService {

    @Autowired
    private final OrderProductRepository orderProductRepository;

    public OrderProductService(OrderProductRepository orderProductRepository) {
        this.orderProductRepository = orderProductRepository;
    }

    /**
     * Sistemde kayıtlı olan OrderProduct nesneleri create işlemleri için kullanılan metoddur.
     * @param orderProduct Eklenecek olan OrderProduct nesnesi
     * @return eklenen orderproduct nesnesi
     */
    public OrderProduct createOrderProduct(OrderProduct orderProduct) {
        return orderProductRepository.save(orderProduct);
    }
}
