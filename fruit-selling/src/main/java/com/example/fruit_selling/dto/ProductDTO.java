package com.example.fruit_selling.dto;

import com.example.fruit_selling.model.Category;
import com.example.fruit_selling.model.Product;

public class ProductDTO {
    private String id;
    private String name;
    private Long price;
    private String detail;
    private String category;
    private Integer quantity;
    private String unit;
    private String image;

    public ProductDTO(Product product, String image) {
        this.id = product.getId();
        this.name = product.getName();
        this.price = product.getPrice();
        this.detail = product.getDetail();
        this.category = product.getCategory().getName();
        this.quantity = product.getQuantity();
        this.unit = product.getUnit().getName();
        this.image = image;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category.getName();
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
