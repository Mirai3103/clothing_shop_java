package com.shop.clothing.common.util;


import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.category.query.getAllCategoriesGroupByParentQuery.GetAllCategoriesGroupByParentQuery;
import com.shop.clothing.common.Cqrs.ISender;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor

public class ClientUtil {
    private final ISender sender;

    public String toJsonString(Object object) {
        return new JSONObject(object).toString();
    }

    public String toVietnameseCurrency(int price) {
        return String.format("%,d", price).replace(',', '.') + "đ";
    }

    public String toVietnameseCurrency(double price) {
        int priceInt = (int) price;
        return String.format("%,d", priceInt).replace(',', '.') + "đ";
    }

    public String toVietnameseCurrency(float price) {
        int priceInt = (int) price;
        return String.format("%,d", priceInt).replace(',', '.') + "đ";
    }

    public List<CategoryDetailDto> getCategoryTree() {
        return sender.send(new GetAllCategoriesGroupByParentQuery()).get();
    }

}
