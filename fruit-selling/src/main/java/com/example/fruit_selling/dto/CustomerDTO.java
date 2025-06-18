package com.example.fruit_selling.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Valid
public class CustomerDTO {
    @NotBlank(message = "Họ không được để trống")
    @Size(min = 1, max = 30, message = "Họ không được vượt quá 30 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Họ chỉ được chứa chữ cái và khoảng trắng")
    @Schema(description = "Họ khách hàng", example = "Nguyễn Văn")
    private String lastName;

    @NotBlank(message = "Tên không được để trống")
    @Size(min = 1, max = 20, message = "Tên không được vượt quá 20 ký tự")
    @Pattern(regexp = "^[\\p{L} ]+$", message = "Tên chỉ được chứa chữ cái và khoảng trắng")
    @Schema(description = "Tên khách hàng", example = "An")
    private String firstName;

    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không đúng định dạng")
    @Schema(description = "Email khách hàng", example = "an.nguyen@example.com")
    private String email;

    @NotBlank(message = "Số điện thoại không được để trống")
    @Pattern(regexp = "^(0[3|5|7|8|9])+([0-9]{8})$", message = "Số điện thoại phải bắt đầu bằng 03, 05, 07, 08 hoặc 09 và có 10 chữ số")
    @Schema(description = "Số điện thoại khách hàng", example = "0912345678")
    private String phone;

    @NotBlank(message = "Địa chỉ không được để trống")
    @Size(max = 100, message = "Địa chỉ không được vượt quá 100 ký tự")
    @Schema(description = "Địa chỉ giao hàng", example = "123 Trần Phú, Ba Đình, Hà Nội")
    private String address;
}
