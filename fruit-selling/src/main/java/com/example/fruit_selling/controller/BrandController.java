package com.example.fruit_selling.controller;

import com.example.fruit_selling.dto.ApiResponse;
import com.example.fruit_selling.dto.BrandResponseDTO;
import com.example.fruit_selling.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
    private final BrandService brandService;

    @Autowired
    public BrandController(BrandService brandService) {
        this.brandService = brandService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BrandResponseDTO>>> getAllActiveBrands(){
        return ResponseEntity.ok(new ApiResponse<>(HttpStatus.OK, brandService.getAllActiveBrands()));
    }
}
