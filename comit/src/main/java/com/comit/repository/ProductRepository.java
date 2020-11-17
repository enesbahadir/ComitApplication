package com.comit.repository;

import com.comit.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE CONCAT(p.name, p.description, p.price) LIKE %?1%")
    List<Product> search(String keyword);
}
