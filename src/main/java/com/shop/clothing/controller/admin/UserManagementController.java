package com.shop.clothing.controller.admin;

import com.shop.clothing.category.command.updateCategory.UpdateCategoryCommand;
import com.shop.clothing.category.query.getAllCategories.GetAllCategoriesQueries;
import com.shop.clothing.common.Cqrs.ISender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@RequestMapping("/admin/user")
@Controller
public class UserManagementController {
    private final ISender sender;

    @GetMapping()
    public String index(Model model) {
        var page = new GetAllCategoriesQueries();
        page.setPageSize(100);
        var allCategories = sender.send(page).get();
        model.addAttribute("categories", allCategories);
        model.addAttribute("updateCategoryRequest", new UpdateCategoryCommand());
        return "admin/user/index";
    }
}
