package com.shop.clothing.service;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Role;
import com.shop.clothing.request.role.AddPermissionToRoleRequest;
import com.shop.clothing.request.role.CreateRoleRequest;
import com.shop.clothing.request.role.UpdateRoleRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface RoleService {
     Result<Role> addRole(CreateRoleRequest createRoleRequest);
     Result<Role> updateRole(UpdateRoleRequest updateRoleRequest);
     Result<Integer> deleteRole(String name);
     Page<Role> getAllRole(int page, int size);
     Optional<Role> getRoleByName(String name);
     Result<Boolean> addPermissionToRole(AddPermissionToRoleRequest addPermissionToRoleRequest);

}
