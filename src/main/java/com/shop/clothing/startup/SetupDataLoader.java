package com.shop.clothing.startup;

import com.shop.clothing.entity.Permission;
import com.shop.clothing.entity.Role;
import com.shop.clothing.entity.User;
import com.shop.clothing.repository.PermissionRepository;
import com.shop.clothing.repository.RoleRepository;
import com.shop.clothing.repository.UserRepository;
import com.shop.clothing.request.permission.CreatePermissionRequest;
import com.shop.clothing.request.role.CreateRoleRequest;
import com.shop.clothing.service.PermissionService;
import com.shop.clothing.service.RoleService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.*;

@Component

public class SetupDataLoader implements
        ApplicationListener<ContextRefreshedEvent> {

    boolean alreadySetup = false;

    private final UserRepository userRepository;

    private final RoleService roleService;

    private final PermissionService permissionService;

    private final PasswordEncoder passwordEncoder;
    private final SeedCategory seedCategory;

    public SetupDataLoader(UserRepository userRepository, RoleService roleService, PermissionService permissionService, PasswordEncoder passwordEncoder, SeedCategory seedCategory) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.permissionService = permissionService;
        this.passwordEncoder = passwordEncoder;
        this.seedCategory = seedCategory;
    }

    @Override
    @Transactional
    public void onApplicationEvent(ContextRefreshedEvent event) {
        var admin = userRepository.findByEmail("admin@admin.com");
        seedCategory.seedCategory();
        
        seedRole();
        seedPermission();
        if (admin.isPresent()) {
            alreadySetup = true;

        }
        if (alreadySetup)
            return;


        createRoleIfNotFound("ROLE_EMPLOYEE", "Nhân viên", "Nhân viên", new ArrayList<>());

        Role adminRole = roleService.getRoleByName("ROLE_ADMIN").orElseThrow();
        User user = new User();
        user.setFirstName("Admin");
        user.setLastName("Admin");
        user.setPasswordHash(passwordEncoder.encode("admin"));
        user.setEmail("admin@admin.com");
        user.setRoles(List.of(adminRole));
        user.setAccountEnabled(true);
        user.setCustomer(false);
        userRepository.save(user);
        this.alreadySetup = true;

    }

    @Transactional
    public Permission createPermissionIfNotFound(String name, String displayName, String description) {

        Permission permission = permissionService.getPermissionByName(name).orElse(null);
        if (permission == null) {
            var result = permissionService.addPermission(CreatePermissionRequest.builder().normalizedName(name).displayName(displayName).description(description).build());
            permission = result.getData();
        }

        return permission;
    }

    @Transactional
    public Role createRoleIfNotFound(
            String name, String displayName, String description, List<Permission> permissions) {

        var role = roleService.getRoleByName(name).orElse(null);
        if (role == null) {
            roleService.addRole(CreateRoleRequest.builder().normalizedName(name).displayName(displayName).description(description).build());
        }
        return role;
    }
    @Transactional
    public void seedRole() {

        createRoleIfNotFound("ROLE_ADMIN", "Quản trị viên", "Quản trị viên", new ArrayList<>());
        createRoleIfNotFound("ROLE_SALES_STAFF", "Nhân viên bán hàng", "Vai trò nhân viên bán hàng", new ArrayList<>());
        createRoleIfNotFound("ROLE_STOCK_CLERK", "Nhân viên nhập hàng", "Vai trò nhân viên nhập hàng", new ArrayList<>());
        createRoleIfNotFound("ROLE_ACCOUNTANT", "Kế toán", "Vai trò kế toán", new ArrayList<>());
        createRoleIfNotFound("ROLE_CUSTOMER", "Khách hàng", "Vai trò khách hàng", new ArrayList<>());
        createRoleIfNotFound("ROLE_MANAGER", "Quản lý", "Vai trò quản lý", new ArrayList<>());

    }
    @Transactional
    public  void seedPermission() {
        createPermissionIfNotFound("CREATE_PRODUCT", "Tạo sản phẩm", "Quyền tạo sản phẩm mới.");
        createPermissionIfNotFound("UPDATE_PRODUCT", "Cập nhật sản phẩm", "Quyền cập nhật thông tin sản phẩm.");
        createPermissionIfNotFound("DELETE_PRODUCT", "Xóa sản phẩm", "Quyền xóa sản phẩm.");
        createPermissionIfNotFound("VIEW_PRODUCT", "Xem sản phẩm", "Quyền xem thông tin sản phẩm.");
        createPermissionIfNotFound("CREATE_ORDER", "Tạo đơn đặt hàng", "Quyền tạo đơn đặt hàng.");
        createPermissionIfNotFound("UPDATE_ORDER", "Cập nhật đơn đặt hàng", "Quyền cập nhật thông tin đơn đặt hàng.");
        createPermissionIfNotFound("DELETE_ORDER", "Xóa đơn đặt hàng", "Quyền xóa đơn đặt hàng.");
        createPermissionIfNotFound("VIEW_ORDER", "Xem đơn đặt hàng", "Quyền xem thông tin đơn đặt hàng.");
        createPermissionIfNotFound("PROCESS_ORDER", "Xử lý đơn đặt hàng", "Quyền xử lý đơn đặt hàng.");
        createPermissionIfNotFound("CREATE_PROMOTION", "Tạo chương trình khuyến mãi", "Quyền tạo chương trình khuyến mãi mới.");
        createPermissionIfNotFound("UPDATE_PROMOTION", "Cập nhật chương trình khuyến mãi", "Quyền cập nhật thông tin chương trình khuyến mãi.");
        createPermissionIfNotFound("DELETE_PROMOTION", "Xóa chương trình khuyến mãi", "Quyền xóa chương trình khuyến mãi.");
        createPermissionIfNotFound("VIEW_PROMOTION", "Xem chương trình khuyến mãi", "Quyền xem thông tin chương trình khuyến mãi.");
        createPermissionIfNotFound("CREATE_USER", "Tạo người dùng", "Quyền tạo người dùng mới.");
        createPermissionIfNotFound("UPDATE_USER", "Cập nhật người dùng", "Quyền cập nhật thông tin người dùng.");
        createPermissionIfNotFound("DELETE_USER", "Xóa người dùng", "Quyền xóa người dùng.");
        createPermissionIfNotFound("VIEW_USER", "Xem người dùng", "Quyền xem thông tin người dùng.");
        createPermissionIfNotFound("CREATE_ROLE", "Tạo vai trò", "Quyền tạo vai trò mới.");
        createPermissionIfNotFound("UPDATE_ROLE", "Cập nhật vai trò", "Quyền cập nhật thông tin vai trò.");
        createPermissionIfNotFound("DELETE_ROLE", "Xóa vai trò", "Quyền xóa vai trò.");
        createPermissionIfNotFound("VIEW_ROLE", "Xem vai trò", "Quyền xem thông tin vai trò.");
        createPermissionIfNotFound("ASSIGN_ROLES", "Gán vai trò", "Quyền gán vai trò cho người dùng.");
        createPermissionIfNotFound("CREATE_PERMISSION", "Tạo quyền", "Quyền tạo quyền mới.");
        createPermissionIfNotFound("UPDATE_PERMISSION", "Cập nhật quyền", "Quyền cập nhật thông tin quyền.");
        createPermissionIfNotFound("DELETE_PERMISSION", "Xóa quyền", "Quyền xóa quyền.");
        createPermissionIfNotFound("VIEW_PERMISSION", "Xem quyền", "Quyền xem thông tin quyền.");
        createPermissionIfNotFound("VIEW_REPORTS", "Xem báo cáo", "Quyền xem báo cáo và thống kê.");
        createPermissionIfNotFound("MANAGE_INVENTORY", "Quản lý tồn kho", "Quyền quản lý tồn kho sản phẩm.");
        createPermissionIfNotFound("MANAGE_PROVIDERS", "Quản lý nhà cung cấp", "Quyền quản lý thông tin nhà cung cấp.");
        createPermissionIfNotFound("MANAGE_IMPORT_INVOICES", "Quản lý hóa đơn nhập hàng", "Quyền quản lý hóa đơn nhập hàng.");
        createPermissionIfNotFound("VIEW_RATINGS", "Xem đánh giá", "Quyền xem đánh giá sản phẩm từ khách hàng.");
        createPermissionIfNotFound("MANAGE_ACCOUNTING", "Quản lý kế toán", "Quyền quản lý công việc kế toán.");
        createPermissionIfNotFound("MANAGE_CUSTOMERS", "Quản lý khách hàng", "Quyền quản lý thông tin khách hàng.");
        createPermissionIfNotFound("MANAGE_BRANDS", "Quản lý thương hiệu", "Quyền quản lý thông tin thương hiệu.");
        createPermissionIfNotFound("ADMIN_DASHBOARD", "Truy cập bảng điều khiển", "Quyền truy cập bảng điều khiển.");
    }
}