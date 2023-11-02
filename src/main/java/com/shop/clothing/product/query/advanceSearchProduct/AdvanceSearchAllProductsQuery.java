package com.shop.clothing.product.query.advanceSearchProduct;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.entity.Product;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdvanceSearchAllProductsQuery extends PaginationRequest implements IRequest<Paginated<ProductBriefDto>> {

    private int[] categoryIds;
    private Product.ProductGender[] forGenders;
    @Builder.Default
    private int minPrice = 0;
    @Builder.Default
    private int maxPrice = Integer.MAX_VALUE;
    private int[] colorIds;
    private String[] sizes;

}
