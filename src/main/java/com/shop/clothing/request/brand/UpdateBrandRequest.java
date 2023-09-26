package com.shop.clothing.request.brand;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class UpdateBrandRequest {
    private String name;
    private int id;
}
