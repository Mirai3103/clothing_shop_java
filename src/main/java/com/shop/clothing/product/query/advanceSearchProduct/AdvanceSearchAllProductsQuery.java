package com.shop.clothing.product.query.advanceSearchProduct;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.dto.ProductBriefDto;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AdvanceSearchAllProductsQuery  extends PaginationRequest implements IRequest<Paginated<ProductBriefDto>> {
}
