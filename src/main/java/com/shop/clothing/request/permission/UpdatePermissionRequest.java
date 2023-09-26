package com.shop.clothing.request.permission;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UpdatePermissionRequest {
    @NotEmpty(message = "Mã quyền không được để trống")
    private String normalizedName;
    @NotEmpty(message = "Tên quyền không được để trống")
    private String displayName;
    @NotEmpty(message = "Mô tả quyền không được để trống")
    private String description;
}
