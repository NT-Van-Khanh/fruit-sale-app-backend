package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ApiResponse;
import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.repository.OrderRepository;
import com.example.fruit_selling.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> addOrder(@RequestBody OrderDTO order){
        ApiResponse<OrderResponseDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo đơn hàng thành công", orderService.addOrder(order));
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> addOrder(@PathVariable String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.CREATED, orderService.getOrderById(id)));
    }
}
