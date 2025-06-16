package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.OrderItemDTO;

public interface OrderItemService {
    void add(OrderItemDTO orderItem, String orderId);
}
