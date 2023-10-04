package com.shop.clothing.controller.admin;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/product")
public class ProductController {

    @GetMapping()
    public String getProducts(Model model) {
        return "admin/product/index";
    }
    @GetMapping("/create")
    public String createProduct(Model model) {
        return "admin/product/create";
    }
    @GetMapping("{id}/view")
    public String viewProduct(Model model, @PathVariable String id) {
        return "admin/product/view";
    }

    @GetMapping("options/{id}/view")
    public String viewProductOptions(Model model, @PathVariable String id) {
        return "admin/product/option/view";
    }
}
