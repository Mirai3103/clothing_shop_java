package com.shop.clothing.category.queries.getCategoryBySlug;

import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.category.CategoryRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetCategoryBySlugQueryHandler implements IRequestHandler<GetCategoryBySlugQuery, CategoryDetailDto> {
    private final CategoryRepository categoryRepository;
    private ModelMapper modelMapper;

    @Override
    public HandleResponse<CategoryDetailDto> handle(GetCategoryBySlugQuery getCategoryBySlugQuery) {
        var category = categoryRepository.findBySlug(getCategoryBySlugQuery.slug());
        if (category.isEmpty()) {
            return HandleResponse.ok(null);
        }
        return HandleResponse.ok(modelMapper.map(category.get(), CategoryDetailDto.class));
    }
}
