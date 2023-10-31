package com.shop.clothing.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/order")

public class OrderAdminController {
    @PreAuthorize("hasAnyAuthority('CREATE_ORDER','UPDATE_ORDER','VIEW_ORDERS')")
    @GetMapping()
    public String getProducts() {
        return "admin/order";
    }
}
