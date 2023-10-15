package com.shop.clothing.product.dto;


import com.shop.clothing.category.CategoryBriefDto;
import com.shop.clothing.common.dto.AuditableDto;
import com.shop.clothing.product.entity.Product;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;


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
    private LocalDateTime deletedDate;
    public String getVietnamesePrice(){
        // 200.000đ
        return String.format("%,.0fđ", price);
    }
    public String getForGenderDisplay(){
        switch (forGender){
            case FOR_BOTH -> {
                return "Nam và nữ";
            }
            case FOR_MALE -> {
                return "Nam";
            }
            case FOR_FEMALE -> {
                return "Nữ";
            }

        }
        return "";
    }
    public int getFinalPrice(){
        return (int) (price*(100.0-discount)/100);
    }
}
