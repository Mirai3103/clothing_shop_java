package com.shop.clothing.controller.admin;

import com.shop.clothing.category.commands.updateCategory.UpdateCategoryCommand;
import com.shop.clothing.category.queries.getAllCategoriesQueries.GetAllCategoriesQueries;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.category.commands.createCategory.CreateCategoryCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@AllArgsConstructor
@Controller
@RequestMapping("/admin/category")

public class CategoryManagementController {
    private final ISender sender;

    @GetMapping()
    public String getCategories(Model model, CreateCategoryCommand createCategoryCommand) {
        var page =new GetAllCategoriesQueries();
        page.setSize(100);
        var allCategories = sender.send(page).get();
        model.addAttribute("categories", allCategories);
        model.addAttribute("createCategoryRequest", createCategoryCommand);
        model.addAttribute("updateCategoryRequest", new UpdateCategoryCommand());
        return "admin/category/index";
    }
}
