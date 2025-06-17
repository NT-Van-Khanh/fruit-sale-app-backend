package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.CustomerDTO;
import com.example.fruit_selling.model.Customer;

public class CustomerMapper {
    public static CustomerDTO toDTO(Customer customer){
        return CustomerDTO.builder()
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .phone(customer.getPhone())
                .email(customer.getEmail())
                .address(customer.getAddress())
                .build();
    }

    public static Customer toEntity(CustomerDTO dto){
        return Customer.builder()
                .lastName(dto.getLastName())
                .firstName(dto.getFirstName())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .build();
    }
}
