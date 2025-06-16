package com.example.fruit_selling.mapper;

import com.example.fruit_selling.dto.ProductDTO;
import com.example.fruit_selling.dto.ProductSimpleDTO;
import com.example.fruit_selling.model.Product;
import com.example.fruit_selling.util.FirebaseImage;

public class ProductMapper {
    public static ProductSimpleDTO toSimpleDTO(Product product){
        return ProductSimpleDTO.builder().id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .unit(product.getUnit().getName())
                .image(FirebaseImage.getImageLink(product.getId()))
                .build();
    }
    public static ProductDTO toDTO(Product product){
        return ProductDTO.builder().id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .unit(product.getUnit().getName())
                .detail(product.getDetail())
                .image(FirebaseImage.getImageLink(product.getId()))
                .category(product.getCategory().getName())
                .brand(product.getBrand().getName())
                .build();
    }
}
