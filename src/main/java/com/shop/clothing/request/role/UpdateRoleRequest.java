package com.shop.clothing.request.role;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateRoleRequest {
    @NotEmpty(message = "Mã vai trò không được để trống")
    private String normalizedName;
    @NotEmpty(message = "Tên vai trò không được để trống")
    private String displayName;
    @NotEmpty(message = "Mô tả vai trò không được để trống")
    private String description;
}
