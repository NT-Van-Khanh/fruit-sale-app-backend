package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.OrderDTO;
import com.example.fruit_selling.dto.OrderResponseDTO;
import com.example.fruit_selling.model.OrderItem;
import com.example.fruit_selling.model.OrderProduct;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

public class OrderMapper {
    public static OrderProduct toOrder(OrderDTO dto){
        Collection<OrderItem> orderItems =dto.getItems().stream().map(OrderItemMapper::toEntity)
                .collect(Collectors.toCollection(ArrayList::new));
        return OrderProduct.builder()
                .customer(CustomerMapper.toEntity(dto.getCustomer()))
                .items(orderItems)
                .address(dto.getAddress())
                .totalCost(dto.getTotalCost())
                .note(dto.getNote())
                .payStatus(dto.getPayStatus())
                .shipStatus(dto.getShipStatus())
                .build();
    }

    public static OrderResponseDTO toResponseDTO( OrderProduct orderProduct){
        return OrderResponseDTO.builder()
                .id(orderProduct.getId())
                .customer(CustomerMapper.toDTO(orderProduct.getCustomer()))
                .items(orderProduct.getItems().stream().map(OrderItemMapper::toResponseDTO).toList())
                .address(orderProduct.getAddress())
                .totalCost(orderProduct.getTotalCost())
                .payStatus(orderProduct.getPayStatus())
                .shipStatus(orderProduct.getShipStatus())
                .createAt(orderProduct.getCreateAt())
                .lastUpdate(orderProduct.getLastUpdate())
                .build();

    }
}
