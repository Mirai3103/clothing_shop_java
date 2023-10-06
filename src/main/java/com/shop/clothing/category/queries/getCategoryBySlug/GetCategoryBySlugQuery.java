package com.shop.clothing.category.queries.getCategoryBySlug;

import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.common.Cqrs.IRequest;

public record GetCategoryBySlugQuery(String slug) implements IRequest<CategoryDetailDto>{


}
