package com.shop.clothing.category.commands.updateCategory;

import com.shop.clothing.common.Cqrs.IRequest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCategoryCommand implements IRequest<Boolean> {
    @Min(1)
    @NotNull(message = "Id không được để trống")
    private int id;
    @NotEmpty(message = "Tên danh mục không được để trống")
    @Min(2)
    @Max(50)
    private String name;

    @NotEmpty(message = "Url không được để trống")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",message = "Url không hợp lệ")
    private  String slug;

    private  int parentId = 0;
}
