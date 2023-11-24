package com.shop.clothing.auth.dto;

import com.shop.clothing.auth.entity.Permission;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDto {
    private String normalizedName;
    private String displayName;
    private String description;
    private java.util.List<PermissionDto> permissions;
}
