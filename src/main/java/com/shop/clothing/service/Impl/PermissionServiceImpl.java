package com.shop.clothing.service.Impl;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Permission;
import com.shop.clothing.repository.PermissionRepository;
import com.shop.clothing.request.permission.CreatePermissionRequest;
import com.shop.clothing.request.permission.UpdatePermissionRequest;
import com.shop.clothing.request.role.AddPermissionToRoleRequest;
import com.shop.clothing.service.PermissionService;
import com.shop.clothing.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private  RoleService roleService;
@Autowired
    public void setRoleService(@Lazy RoleService roleService) {
        this.roleService = roleService;
    }

    public PermissionServiceImpl(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @Override
    public Result<Permission> addPermission(@Valid CreatePermissionRequest createPermissionRequest) {
         var existPermission = permissionRepository.findById(createPermissionRequest.getNormalizedName());
        if (existPermission.isPresent()) {
            return Result.error("Quyền với mã " + createPermissionRequest.getNormalizedName() + " đã tồn tại");
        }
        var permission = Permission.builder()
                .normalizedName(createPermissionRequest.getNormalizedName())
                .displayName(createPermissionRequest.getDisplayName())
                .description(createPermissionRequest.getDescription())
                .build();
        permissionRepository.save(permission);
        roleService.addPermissionToRole(AddPermissionToRoleRequest.builder()
                .permissionName(permission.getNormalizedName())
                .roleName("ROLE_ADMIN")
                .build());
        return Result.success(permission);
    }

    @Override
    public Result<Permission> updatePermission(@Valid UpdatePermissionRequest updatePermissionRequest) {
        var existPermission = permissionRepository.findById(updatePermissionRequest.getNormalizedName());
        if (existPermission.isEmpty()) {
            return Result.error("Quyền với mã " + updatePermissionRequest.getNormalizedName() + " không tồn tại");
        }
        var permission = existPermission.get();
        permission.setDisplayName(updatePermissionRequest.getDisplayName());
        permission.setDescription(updatePermissionRequest.getDescription());
        permissionRepository.save(permission);
        return Result.success(permission);
    }

    @Override
    public Result<Integer> deletePermission(String name) {
       var existPermission = permissionRepository.findById(name);
         if (existPermission.isEmpty()) {
              return Result.error("Quyền với mã " + name + " không tồn tại");
         }
            permissionRepository.deleteById(name);
            return Result.success(1);
    }

    @Override
    public Page<Permission> getAllPermission(int page, int size) {
        var pageable = PageRequest.of(page, size);
        return permissionRepository.findAll(pageable);
    }

    @Override
    public Optional<Permission> getPermissionByName(String name) {
        return permissionRepository.findById(name);
    }
}
