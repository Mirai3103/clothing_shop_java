package com.shop.clothing.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductOptionDetailDto extends ProductOptionDto{
    private ProductBriefDto product;
}
