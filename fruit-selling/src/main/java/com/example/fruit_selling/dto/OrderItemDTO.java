package com.example.fruit_selling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderItemDTO {
    @NotBlank(message = "ID sản phẩm không được để trống")
    @Schema(description = "ID sản phẩm", example = "product123")
    private String productId;

    @Min(value = 1, message = "Số lượng sản phẩm phải lớn hơn 0")
    @Schema(description = "Số lượng mua", example = "2")
    private Integer quantity;
}
