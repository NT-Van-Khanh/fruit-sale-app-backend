package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ApiResponse;
import com.example.fruit_selling.dto.CategoryResponseDTO;
import com.example.fruit_selling.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<CategoryResponseDTO>>> getAllActiveCategories(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, categoryService.getAllActiveCategories()));
    }
}
