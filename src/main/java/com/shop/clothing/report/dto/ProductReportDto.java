package com.shop.clothing.report.dto;

import com.shop.clothing.product.dto.ProductOptionDetailDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductReportDto {
    private Date date;
    private ProductOptionDetailDto productOption;
    private int totalSold;
}
