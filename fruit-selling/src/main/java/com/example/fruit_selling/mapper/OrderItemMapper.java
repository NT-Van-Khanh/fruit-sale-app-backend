package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.OrderItemDTO;
import com.example.fruit_selling.dto.OrderItemResponseDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.Product;

public class OrderItemMapper {
    public static OrderItem toEntity(OrderItemDTO dto){
        return OrderItem.builder()
                .product(new Product(dto.getProductId()))
                .quantity(dto.getQuantity())
                .build();
    }

    public static OrderItemResponseDTO toResponseDTO(OrderItem orderItem){
        return OrderItemResponseDTO.builder()
                .id(orderItem.getId())
                .product(ProductMapper.toSimpleDTO(orderItem.getProduct()))
                .quantity(orderItem.getQuantity())
                .price(orderItem.getPrice())
                .build();
    }
}
