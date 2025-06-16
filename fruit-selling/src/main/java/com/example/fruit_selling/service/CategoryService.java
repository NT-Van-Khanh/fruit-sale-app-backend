package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryResponseDTO> getAllActiveCategories();
}
