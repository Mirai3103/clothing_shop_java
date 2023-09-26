package com.shop.clothing.service.Impl;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Category;
import com.shop.clothing.repository.CategoryRepository;
import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.request.category.CreateCategoryRequest;
import com.shop.clothing.request.category.UpdateCategoryRequest;
import com.shop.clothing.service.CategoryService;
import com.shop.clothing.util.SlugUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final SlugUtil slugUtil;

    @Override
    public Result<Category> createCategory(CreateCategoryRequest category) {
        var existWithName = categoryRepository.findByName(category.getName());
        if (existWithName.isPresent()) {
            return Result.error("Tên danh mục đã tồn tại");
        }
        var parentCategory = categoryRepository.findById(category.getParentId());
        if (category.getParentId() != 0 && parentCategory.isEmpty()) {
            return Result.error("Danh mục cha không tồn tại");
        }
        var newCategory = new Category();
        newCategory.setSlug(slugUtil.slugify(category.getName()));
        newCategory.setName(category.getName());
        newCategory.setParent(parentCategory.orElse(null));
        categoryRepository.save(newCategory);
        return Result.success(newCategory);
    }

    @Override
    public Result<Category> updateCategory(UpdateCategoryRequest category) {
        var exist = categoryRepository.findById(category.getId());
        if (exist.isEmpty()) {
            return Result.error("Danh mục không tồn tại");
        }
        var isUpdateName = exist.get().getName().equals(category.getName());
        var isUpdateSlug = exist.get().getSlug().equals(category.getSlug());
        var isUpdateParent = exist.get().getParent() == null && category.getParentId() == 0 || exist.get().getParent() != null && exist.get().getParent().getCategoryId() == category.getParentId();
        if (isUpdateName && isUpdateSlug && isUpdateParent) {
            return Result.error("Không có gì thay đổi");
        }
        if (!isUpdateName) {
            var existWithName = categoryRepository.findByName(category.getName());
            if (existWithName.isPresent()) {
                return Result.error("Tên danh mục đã tồn tại");
            }
            exist.get().setName(category.getName());
        }
        if (!isUpdateSlug) {
            var existWithSlug = categoryRepository.findBySlug(category.getSlug());
            if (existWithSlug.isPresent()) {
                return Result.error("Slug danh mục đã tồn tại");
            }
            exist.get().setSlug(category.getSlug());
        }
        if (!isUpdateParent) {
            var parentCategory = categoryRepository.findById(category.getParentId());
            if (category.getParentId() != 0 && parentCategory.isEmpty()) {
                return Result.error("Danh mục cha không tồn tại");
            }
            exist.get().setParent(parentCategory.orElse(null));
        }
        categoryRepository.save(exist.get());
        return Result.success(exist.get());
    }

    @Override
    public Result<Boolean> deleteCategory(int id) {
        var exist = categoryRepository.findById(id);
        if (exist.isEmpty()) {
            return Result.error("Danh mục không tồn tại");
        }
        categoryRepository.delete(exist.get());
        return Result.success(true);
    }

    @Override
    public Optional<Category> getCategory(int id) {
        return categoryRepository.findById(id);
    }

    @Override
    public Page<Category> getAllCategories(PaginationRequest paginationRequest) {

        if (!paginationRequest.getKeyword().isBlank()) {
            return categoryRepository.findAllByNameContaining(paginationRequest.getKeyword(), paginationRequest.getPageable());
        }
        return categoryRepository.findAll(paginationRequest.getPageable());
    }

    @Override
    public Page<Category> getAllCategoriesByParentId(int parentId, PaginationRequest paginationRequest) {
        return  categoryRepository.findAllByParentCategoryId(parentId,paginationRequest.getPageable());
    }

    @Override
    public Page<Category> getAllChildCategories(PaginationRequest paginationRequest) {
        return  categoryRepository.findAllByParentCategoryIdIsNotNull(paginationRequest.getPageable());
    }

    @Override
    public Page<Category> getAllParentCategories(PaginationRequest paginationRequest) {
        return  categoryRepository.findAllByParentCategoryIdIsNull(paginationRequest.getPageable());
    }
}
