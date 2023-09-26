package com.shop.clothing.service;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Category;
import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.request.category.CreateCategoryRequest;
import com.shop.clothing.request.category.UpdateCategoryRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface CategoryService {
    Result<Category> createCategory(CreateCategoryRequest category);
    Result<Category> updateCategory(UpdateCategoryRequest category);
    Result<Boolean> deleteCategory(int id);
    Optional<Category> getCategory(int id);
    Page<Category> getAllCategories(PaginationRequest paginationRequest);
    Page<Category> getAllCategoriesByParentId(int parentId, PaginationRequest paginationRequest);
    Page<Category> getAllChildCategories(PaginationRequest paginationRequest);
    Page<Category> getAllParentCategories(PaginationRequest paginationRequest);




}
