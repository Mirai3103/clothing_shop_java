package com.shop.clothing.category.queries.getCategoryById;

import com.shop.clothing.category.CategoryBriefDto;
import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.common.Cqrs.IRequest;

public record GetCategoryById (int id) implements IRequest<CategoryDetailDto>{

}
