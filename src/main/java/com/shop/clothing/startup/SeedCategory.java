package com.shop.clothing.startup;


import com.shop.clothing.entity.Category;
import com.shop.clothing.repository.CategoryRepository;
import com.shop.clothing.request.category.CreateCategoryRequest;
import com.shop.clothing.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SeedCategory {
    private final CategoryRepository categoryRepository;
    private final CategoryService categoryService;

    public void seedCategory() {
        if (categoryRepository.count() != 0) {
            return;
        }
        var ao = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo")
                .build());
        var quan = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần")
                .build());
        var giay = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Giày")
                .build());
        var tat = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Tất")
                .build());
        var aoTankTop = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo tank top")
                .parentId(ao.getData().getCategoryId())
                .build());
        var aoThun = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo T-shirt")
                .parentId(ao.getData().getCategoryId())
                .build());
        var aoKhoac = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo khoác")
                .parentId(ao.getData().getCategoryId())
                .build());
        var aoSoMi = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo sơ mi")
                .parentId(ao.getData().getCategoryId())
                .build());
        var aoPolo = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo Polo")
                .parentId(ao.getData().getCategoryId())
                .build());
        var aoTheThao = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Áo thể thao")
                .parentId(ao.getData().getCategoryId())
                .build());
        var quanDai = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần dài")
                .parentId(quan.getData().getCategoryId())
                .build());
        var quanShort = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần short")
                .parentId(quan.getData().getCategoryId())
                .build());
        var quanJogger = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần jogger")
                .parentId(quan.getData().getCategoryId())
                .build());
        var quanKaki = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần kaki")
                .parentId(quan.getData().getCategoryId())
                .build());
        var quanJean = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần jean")
                .parentId(quan.getData().getCategoryId())
                .build());
        var giayTheThao = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Giày thể thao")
                .parentId(giay.getData().getCategoryId())
                .build());
        var giayTay = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Giày tây")
                .parentId(giay.getData().getCategoryId())
                .build());
        var giayBoot = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Giày boot")
                .parentId(giay.getData().getCategoryId())
                .build());
        var quanLot = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Quần lót")
                .parentId(quan.getData().getCategoryId())
                .build());
        var vay = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Chân váy")
                .build());
        var dam = categoryService.createCategory(CreateCategoryRequest.builder()
                .name("Đầm")
                .build());

    }
}
