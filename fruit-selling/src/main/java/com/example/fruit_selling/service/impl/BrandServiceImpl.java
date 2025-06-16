package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.mapper.BrandMapper;
import com.example.fruit_selling.dto.BrandResponseDTO;
import com.example.fruit_selling.model.Brand;
import com.example.fruit_selling.repository.BrandRepository;
import com.example.fruit_selling.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<BrandResponseDTO> getAllActiveBrands() {
        List<Brand>  brands= brandRepository.findByFlag(false);
        return brands.stream()
                .map(BrandMapper::toResponseDTO)
                .toList();
    }
}
