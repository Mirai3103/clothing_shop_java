package com.shop.clothing.service;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Permission;
import com.shop.clothing.request.permission.CreatePermissionRequest;
import com.shop.clothing.request.permission.UpdatePermissionRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface PermissionService {
     Result<Permission> addPermission(CreatePermissionRequest createPermissionRequest);
     Result<Permission> updatePermission(UpdatePermissionRequest updatePermissionRequest);
     Result<Integer> deletePermission(String name);
     Page<Permission> getAllPermission(int page, int size);
     Optional<Permission> getPermissionByName(String name);
}
