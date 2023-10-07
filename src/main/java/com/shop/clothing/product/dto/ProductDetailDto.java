package com.shop.clothing.product.dto;

import com.shop.clothing.brand.Brand;
import com.shop.clothing.category.Category;
import com.shop.clothing.category.CategoryBriefDto;
import com.shop.clothing.common.dto.AuditableDto;
import com.shop.clothing.product.entity.Product;
import com.shop.clothing.product.entity.ProductImage;
import com.shop.clothing.product.entity.ProductOption;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class ProductDetailDto extends ProductBriefDto {

    private java.util.List<ProductOptionDto> productOptions;
    private java.util.List<ProductImageDto>images;

}
