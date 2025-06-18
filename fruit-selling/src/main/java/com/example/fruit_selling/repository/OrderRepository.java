package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderProduct, String> {
    Page<OrderProduct> findByCustomerEmail(String email, Pageable pageable);
}
