package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.Page;
import com.example.fruit_selling.dto.PageResponse;
import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

public interface ProductService {
    ProductDTO getProductById(String id);
    ProductSimpleDTO getProductSimpleById(String id);
//    List<Product> getAll();
    PageResponse<ProductSimpleDTO> searchProducts(String keyword, String categoryId, String brandId, Page pageRequest);
    List<ProductSimpleDTO> getTop5Product();
    List<ProductSimpleDTO> getProductsForHomepage();
    Integer getProductQuantityById(String id);
}
