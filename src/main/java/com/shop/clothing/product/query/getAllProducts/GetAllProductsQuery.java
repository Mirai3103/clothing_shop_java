package com.shop.clothing.product.query.getAllProducts;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.entity.Product;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetAllProductsQuery extends PaginationRequest implements IRequest<Paginated<ProductBriefDto>> {
    private int categoryId;
    private Product.ProductGender forGender;
    private int minPrice = 0;
    private int maxPrice = Integer.MAX_VALUE;
}
