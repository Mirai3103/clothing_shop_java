package com.shop.clothing.product.command.createProduct;

import com.shop.clothing.brand.Brand;
import com.shop.clothing.category.Category;
import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.product.entity.Product;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.URL;


@Getter
@Setter
@Builder
public class CreateProductCommand implements IRequest<Integer> {
    @NotEmpty(message = "Tên sản phẩm không được để trống")
    @Size(min = 5, max = 100, message = "Tên sản phẩm phải có độ dài từ 5 đến 100 ký tự")
    private String name;
    @Builder.Default
    private Product.ProductGender forGender = Product.ProductGender.FOR_BOTH;
    @NotEmpty(message = "Mô tả sản phẩm không được để trống")
    private String description;
    @NotNull(message = "Giá sản phẩm không được để trống")
    @Min(value = 1000, message = "Giá sản phẩm phải lớn hơn 1000")
    private double price;
    @Min(value = 0, message = "Giảm giá sản phẩm phải lớn hơn 0")
    @Builder.Default
    private double discount = 0;
    @NotEmpty(message = "Ảnh sản phẩm không được để trống")
    @URL(message = "Ảnh sản phẩm không hợp lệ")
    private String displayImage;
    private int brandId;
    @NotNull(message = "Danh mục sản phẩm không được để trống")
    @Min(value = 1, message = "Danh mục sản phẩm không hợp lệ")
    private int categoryId;

}
