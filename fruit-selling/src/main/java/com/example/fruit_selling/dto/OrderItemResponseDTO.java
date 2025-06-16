package com.example.fruit_selling.dto;

import com.example.fruit_selling.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItemResponseDTO {
    private Integer id;
    private ProductSimpleDTO product;
    private Integer quantity;
    private Long price;
}
