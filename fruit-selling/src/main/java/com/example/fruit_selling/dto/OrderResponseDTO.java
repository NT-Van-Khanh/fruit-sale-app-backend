package com.example.fruit_selling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderResponseDTO {
    private String id;
    private CustomerDTO customer;
    private Long totalCost;
    private String address;
    private String payStatus;
    private String shipStatus;
    private LocalDateTime createAt;
    private LocalDateTime lastUpdate;
    private String note;
    private List<OrderItemResponseDTO> items;

    public String getFormattedCreateAt() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm dd/MM/yyyy");
        return createAt.format(formatter);
    }

    public String getFormattedLastUpdate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm đ/MM/yyyy");
        return lastUpdate.format(formatter);
    }
}
