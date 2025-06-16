package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.CategoryResponseDTO;
import com.example.fruit_selling.mapper.CategoryMapper;
import com.example.fruit_selling.model.Category;
import com.example.fruit_selling.repository.CategoryRepository;
import com.example.fruit_selling.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CategoryServiceImpl implements CategoryService {
    private  final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryResponseDTO> getAllActiveCategories() {
        List<Category> categories = categoryRepository.findByFlag(false);
        return categories.stream()
                .map(CategoryMapper::toResponseDTO)
                .toList();
    }
}
