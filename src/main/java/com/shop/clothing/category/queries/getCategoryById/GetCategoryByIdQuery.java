package com.shop.clothing.category.queries.getCategoryById;

import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.common.Cqrs.IRequest;

public record GetCategoryByIdQuery(int id) implements IRequest<CategoryDetailDto>{

}
