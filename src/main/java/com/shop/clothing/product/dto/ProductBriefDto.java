package com.shop.clothing.product.dto;


import com.shop.clothing.category.CategoryBriefDto;
import com.shop.clothing.common.dto.AuditableDto;
import com.shop.clothing.product.entity.Product;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ProductBriefDto  extends AuditableDto {
    private int productId;
    private String name;

    private Product.ProductGender forGender = Product.ProductGender.FOR_BOTH;

    private String slug;
    private double price;
    private double discount;

    private String displayImage;
    private CategoryBriefDto category;

}
