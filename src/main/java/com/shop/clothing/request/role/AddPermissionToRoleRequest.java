package com.shop.clothing.request.role;

import jakarta.validation.constraints.NotEmpty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddPermissionToRoleRequest {
    @NotEmpty(message = "Tên vai trò không được để trống")
    private String roleName;
    @NotEmpty(message = "Tên quyền không được để trống")
    private String permissionName;
}
