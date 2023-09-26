package com.shop.clothing.request.category;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateCategoryRequest {
    @NotEmpty(message = "Tên danh mục không được để trống")
    @Min(2)
    @Max(50)
        private String name;

    private  int parentId = 0;
}
