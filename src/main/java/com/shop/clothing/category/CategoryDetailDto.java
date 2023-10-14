package com.shop.clothing.category;

import com.shop.clothing.common.dto.AuditableDto;

public class CategoryDetailDto extends AuditableDto {
    private int categoryId;
    private String name;
    private CategoryBriefDto parent;
    private CategoryBriefDto children;
}
