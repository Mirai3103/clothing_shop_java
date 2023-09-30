package com.shop.clothing.category.queries.getAllCategoriesQueries;

import com.shop.clothing.category.CategoryDto;
import com.shop.clothing.category.CategoryRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.dto.Paginated;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class GetAllCategoriesQueriesHandler implements IRequestHandler<GetAllCategoriesQueries, Paginated<CategoryDto>> {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    @Transactional
    public HandleResponse<Paginated<CategoryDto>> handle(GetAllCategoriesQueries getAllCategoriesQueries) {
        var categories = categoryRepository.findAll(
                getAllCategoriesQueries.getPageable("categoryId"));
        return HandleResponse.ok(Paginated.of(categories.map(category -> modelMapper.map(category, CategoryDto.class))));
    }

}