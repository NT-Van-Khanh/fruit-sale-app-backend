package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.BrandResponseDTO;
import com.example.fruit_selling.model.Brand;

public class BrandMapper {
    public static BrandResponseDTO toResponseDTO(Brand brand) {
        return BrandResponseDTO.builder()
                .id(brand.getId())
                .name(brand.getName())
                .build();
    }
}
