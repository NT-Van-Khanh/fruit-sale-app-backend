package com.example.fruit_selling.dto;

import com.example.fruit_selling.model.Category;
import com.example.fruit_selling.model.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private String id;
    private String name;
    private Long price;
    private String detail;
    private String category;
    private Integer quantity;
    private String unit;
    private String image;
    private String brand;

    public ProductDTO(Product product, String image) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.detail = product.getDetail();
        this.category = product.getCategory().getName();
        this.quantity = product.getQuantity();
        this.unit = product.getUnit().getName();
        this.image = image;
        this.brand = product.getBrand().getName();
    }

}
