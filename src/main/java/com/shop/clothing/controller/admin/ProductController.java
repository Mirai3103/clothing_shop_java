package com.shop.clothing.controller.admin;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.query.getAllProducts.GetAllProductsQuery;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/product")
@PreAuthorize("hasAnyAuthority('CREATE_PRODUCT','UPDATE_PRODUCT')")
public class ProductController {
    private final ISender sender;
    @GetMapping()
    public String getProducts(Model model, GetAllProductsQuery getAllProductsQuery) {
        getAllProductsQuery.setIncludeDeleted(true);
        var allProducts = sender.send(getAllProductsQuery).get();
        model.addAttribute("products", allProducts);
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

    @GetMapping("{id}/options/create")
    public String createProductOption(Model model, @PathVariable String id) {
        return "admin/product/option/create";
    }
    @GetMapping("options/{id}/view")
    public String viewProductOptions(Model model, @PathVariable String id) {
        return "admin/product/option/view";
    }
}
