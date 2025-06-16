package com.example.fruit_selling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class OrderDTO {
    @Valid
    @NotNull(message = "Thông tin khách hàng không được để trống")
    private CustomerDTO customer;

    @NotNull(message = "Tổng tiền không được để trống")
    @PositiveOrZero(message = "Tổng tiền không được âm")
    @Schema(description = "Tổng chi phí đơn hàng", example = "150000")
    private Long totalCost;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Schema(description = "Địa chỉ giao hàng", example = "123 Trần Phú, Hà Nội")
    private String address;

    @NotBlank(message = "Trạng thái thanh toán không được để trống")
    @Schema(description = "Trạng thái thanh toán", example = "PAID")
    private String payStatus;

    @NotBlank(message = "Trạng thái vận chuyển không được để trống")
    @Schema(description = "Trạng thái vận chuyển", example = "SHIPPING")
    private String shipStatus;

    @Schema(description = "Thời gian tạo đơn hàng")
    private LocalDateTime createAt;

    @Schema(description = "Ghi chú đơn hàng", example = "Giao giờ hành chính")
    private String note;

    @Valid
    @NotEmpty(message = "Đơn hàng phải có ít nhất một sản phẩm")
    private List<OrderItemDTO> items;
}
