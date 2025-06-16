package com.example.fruit_selling.service.impl;

import com.example.fruit_selling.dto.Page;
import com.example.fruit_selling.dto.PageResponse;
import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.exception.ResourceNotFoundException;
import com.example.fruit_selling.mapper.ProductMapper;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.repository.ProductRepository;
import com.example.fruit_selling.service.ProductService;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO getProductById(String id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sản phẩm không tồn tại!"));
        return ProductMapper.toDTO(product);
    }

    public ProductSimpleDTO getProductSimpleById(String id){
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sản phẩm không tồn tại!"));
        return ProductMapper.toSimpleDTO(product);
    }

    @Override
    public PageResponse<ProductSimpleDTO> searchProducts(String keyword, String categoryId, String brandId, Page pageRequest) {
        Pageable pageable = pageRequest.toPageable();
        org.springframework.data.domain.Page<Product> page = productRepository.searchProducts(keyword, categoryId, brandId, pageable);
        List<ProductSimpleDTO> productSimpleDTOS = page.getContent().stream()
                .map(ProductMapper::toSimpleDTO)
                .collect(Collectors.toList());
        return  new PageResponse<>(productSimpleDTOS, page);
    }

    @Override
    public List<ProductSimpleDTO> getTop5Product() {
        List<Product> products = productRepository.findTop5ByOrderByIdDesc(); // Lấy 8 sản phẩm mới nhất

        return products.stream()
                .map(ProductMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductSimpleDTO> getProductsForHomepage() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::toSimpleDTO)
                .collect(Collectors.toList());
    }

    @Override
    public Integer getProductQuantityById(String id){
        Integer quantity = productRepository.getProductQuantityById(id);
        if (quantity == null) {
            throw new ResourceNotFoundException("Product not found with id: " + id);
        }
        return quantity;
    }
}
