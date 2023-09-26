package com.shop.clothing.controller.admin;

import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.service.BrandService;
import lombok.AllArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/brand")
public class BrandManagementController {
    private final BrandService brandService;

    @GetMapping()
    public ModelAndView getBrands() {
        return new ModelAndView("admin/brand/index");
    }
}
