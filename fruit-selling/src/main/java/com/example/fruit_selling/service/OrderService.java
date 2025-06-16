package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.dto.PageResponse;

public interface OrderService {
    OrderResponseDTO addOrder(OrderDTO orderDTO);
    PageResponse<OrderResponseDTO> getOrdersByEmail(String email, String otp);
    OrderResponseDTO getOrderById(String id);
}
