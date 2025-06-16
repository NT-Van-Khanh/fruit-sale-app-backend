package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.BrandResponseDTO;
import com.example.fruit_selling.model.Brand;

import java.util.List;

public interface BrandService {
    List<BrandResponseDTO> getAllActiveBrands();
}
