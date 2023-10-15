package com.shop.clothing.category.command.updateCategory;

import com.shop.clothing.common.Cqrs.IRequest;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UpdateCategoryCommand implements IRequest<Boolean> {
    @Min(1)
    @NotNull(message = "Id không được để trống")
    private int id;
    @NotEmpty(message = "Tên danh mục không được để trống")
    @Length(min = 3, max = 50, message = "Tên danh mục phải từ 3 đến 50 ký tự")
    private String name;

    @NotEmpty(message = "Url không được để trống")
    @Pattern(regexp = "^[a-z0-9]+(?:-[a-z0-9]+)*$",message = "Url không hợp lệ, chỉ chứa chữ thường, số và dấu gạch ngang")
    private  String slug;

    private  int parentId = 0;
}
