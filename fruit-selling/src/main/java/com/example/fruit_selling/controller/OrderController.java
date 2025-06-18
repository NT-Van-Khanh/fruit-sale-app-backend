package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ApiResponse;
import com.example.fruit_selling.dto.EmailRequest;
import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RequestMapping("/api/orders")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;
    @PostMapping("/add")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> addOrder(@RequestBody @Valid OrderDTO order,@RequestParam String otp){
        ApiResponse<OrderResponseDTO> apiResponse = new ApiResponse<>(HttpStatus.CREATED.value(), "Tạo đơn hàng thành công", orderService.addOrder(order, otp));
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


    @PostMapping("/verify-email")
    public ResponseEntity<ApiResponse<String>> sendOtp(@RequestBody @Valid EmailRequest request){
        orderService.sendOtp(request.getEmail(),orderService.VERIFY,"Mã OTP xác thực tài khoản");
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK,"Vui lòng kiểm tra email để lấy mã OTP"));
    }


    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrderResponseDTO>> getOrder(@PathVariable String id){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, orderService.getOrderById(id)));
    }
}
