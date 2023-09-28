package com.shop.clothing.category.queries.getAllCategoriesQueries;

import com.shop.clothing.category.CategoryDto;
import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import lombok.*;


@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCategoriesQueries extends PaginationRequest implements IRequest<Paginated<CategoryDto>> {
    private String eyword = "";
}