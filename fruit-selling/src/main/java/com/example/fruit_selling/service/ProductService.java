package com.example.fruit_selling.service;

import com.example.fruit_selling.dto.Page;
import com.example.fruit_selling.dto.PageResponse;
import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;

import java.util.List;

public interface ProductService {
    ProductDTO getProductById(String id);
    ProductSimpleDTO getProductSimpleById(String id);
//    List<Product> getAll();
    PageResponse<ProductSimpleDTO> searchProducts(String keyword, String categoryId,String brandId,
                                                  Long minPrice, Long maxPrice, Page pageRequest);
    List<ProductSimpleDTO> getTop5Product();
    List<ProductSimpleDTO> getProductsForHomepage();
    Integer getProductQuantityById(String id);
}
