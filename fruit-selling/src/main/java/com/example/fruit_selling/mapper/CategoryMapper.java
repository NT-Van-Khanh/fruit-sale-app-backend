package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.CategoryResponseDTO;
import com.example.fruit_selling.model.Category;

public class CategoryMapper {
    public static CategoryResponseDTO toResponseDTO(Category category) {
        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .build();
    }
}
