package com.shop.clothing.category.commands.updateCategory;

import com.shop.clothing.category.CategoryRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.util.SlugUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class UpdateCategoryCommandHandler implements IRequestHandler<UpdateCategoryCommand, Void> {
    private final CategoryRepository categoryRepository;

    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateCategoryCommand updateCategoryCommand) {
        var exist = categoryRepository.findById(updateCategoryCommand.getId());
        if (exist.isEmpty()) {
            return HandleResponse.error("Danh mục không tồn tại");
        }
        var isUpdateName = exist.get().getName().equals(updateCategoryCommand.getName());
        var isUpdateSlug = exist.get().getSlug().equals(updateCategoryCommand.getSlug());
        var isUpdateParent = exist.get().getParent() == null && updateCategoryCommand.getParentId() == 0 || exist.get().getParent() != null && exist.get().getParent().getCategoryId() == updateCategoryCommand.getParentId();
        if (isUpdateName && isUpdateSlug && isUpdateParent) {
            return HandleResponse.error("Không có gì thay đổi");
        }

        if (!isUpdateName) {
            var existWithName = categoryRepository.findByName(updateCategoryCommand.getName());
            if (existWithName.isPresent()) {
                return HandleResponse.error("Tên danh mục đã tồn tại");
            }
            exist.get().setName(updateCategoryCommand.getName());
        }
        if (!isUpdateSlug) {
            var existWithSlug = categoryRepository.findBySlug(updateCategoryCommand.getSlug());
            if (existWithSlug.isPresent()) {
                return HandleResponse.error("Url danh mục đã tồn tại");
            }
            exist.get().setSlug(updateCategoryCommand.getSlug());
        }
        if (!isUpdateParent) {
            var parentCategory = categoryRepository.findById(updateCategoryCommand.getParentId());
            if (updateCategoryCommand.getParentId() != 0 && parentCategory.isEmpty()) {
                return HandleResponse.error("Danh mục cha không tồn tại");
            }
            exist.get().setParent(parentCategory.orElse(null));
        }
        categoryRepository.save(exist.get());
        return HandleResponse.ok();
    }
}