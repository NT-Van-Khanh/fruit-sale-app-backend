package com.example.fruit_selling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {
    private CustomerDTO customer;
    private Long totalCost;
    private String address;
    private String payStatus;
    private String shipStatus;
    private LocalDateTime createAt;
    private String note;
    private List<OrderItemDTO> items;
}
