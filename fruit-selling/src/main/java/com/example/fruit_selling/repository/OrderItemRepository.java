package com.example.fruit_selling.repository;

import com.example.fruit_selling.dto.OrderItemResponseDTO;
import com.example.fruit_selling.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
