package com.example.fruit_selling.repository;

import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderProduct, String> {
}
