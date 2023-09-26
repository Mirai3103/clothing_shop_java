package com.shop.clothing.service.Impl;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Role;
import com.shop.clothing.repository.RoleRepository;
import com.shop.clothing.request.role.AddPermissionToRoleRequest;
import com.shop.clothing.request.role.CreateRoleRequest;
import com.shop.clothing.request.role.UpdateRoleRequest;
import com.shop.clothing.service.PermissionService;
import com.shop.clothing.service.RoleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    private final PermissionService permissionService;
    @Override
    public Result<Role> addRole(@Valid CreateRoleRequest createRoleRequest) {
        var existRole = roleRepository.findById(createRoleRequest.getNormalizedName());
        if (existRole.isPresent()) {
            return Result.error("Role with name " + createRoleRequest.getNormalizedName() + " already exist");
        }
        var role = Role.builder()
                .normalizedName(createRoleRequest.getNormalizedName())
                .displayName(createRoleRequest.getDisplayName())
                .description(createRoleRequest.getDescription())
                .build();
        roleRepository.save(role);
        return Result.success(role);
    }

    @Override
    public Result<Role> updateRole(@Valid UpdateRoleRequest updateRoleRequest) {
        var existRole = roleRepository.findById(updateRoleRequest.getNormalizedName());
        if (existRole.isEmpty()) {
            return Result.error("Role with name " + updateRoleRequest.getNormalizedName() + " not found");
        }
        var role = existRole.get();
        role.setDisplayName(updateRoleRequest.getDisplayName());
        role.setDescription(updateRoleRequest.getDescription());
        roleRepository.save(role);
        return Result.success(role);

    }

    @Override
    public Result<Integer> deleteRole(String name) {
        var existRole = roleRepository.findById(name);
        if (existRole.isEmpty()) {
            return Result.error("Role with name " + name + " not found");
        }
        roleRepository.deleteById(name);
        return Result.success(1);
    }

    @Override
    public Page<Role> getAllRole(int page, int size) {
        return roleRepository.findAll(PageRequest.of(page, size));
    }

    @Override
    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findById(name);
    }

    @Override
    public Result<Boolean> addPermissionToRole(@Valid AddPermissionToRoleRequest addPermissionToRoleRequest) {
        var role = roleRepository.findById(addPermissionToRoleRequest.getRoleName());
        if (role.isEmpty()) {
            return Result.error("Role with name " + addPermissionToRoleRequest.getRoleName() + " not found");
        }
        var permission = permissionService.getPermissionByName(addPermissionToRoleRequest.getPermissionName());
        if (permission.isEmpty()) {
            return Result.error("Permission with name " + addPermissionToRoleRequest.getPermissionName() + " not found");
        }
        var role1 = role.get();
        var listPermission = role1.getPermissions();
        if (listPermission == null) {
            listPermission = new ArrayList<>();
        }
        listPermission.add(permission.get());
        role1.setPermissions(listPermission);
        roleRepository.save(role1);
        return Result.success(true);
    }
}
