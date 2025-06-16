package com.example.fruit_selling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ProductSimpleDTO {
    private String id;
    private String name;
    private Long price;
    private Integer quantity;
    private String unit;
    private String image; // URL Firebase

}
