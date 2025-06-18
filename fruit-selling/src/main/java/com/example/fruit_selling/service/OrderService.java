package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.dto.Page;
import com.example.fruit_selling.dto.PageResponse;

public interface OrderService {
    String VERIFY = "VERIFY";
    String GET_ORDER = "GET_ORDER";
    OrderResponseDTO addOrder(OrderDTO orderDTO, String otp);
    PageResponse<OrderResponseDTO> getOrdersByEmail(String email, String otp, Page page);
    OrderResponseDTO getOrderById(String id);

    void sendOtp(String email, String purpose,  String subject);
}
