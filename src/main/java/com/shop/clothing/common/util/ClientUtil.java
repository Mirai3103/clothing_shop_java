package com.shop.clothing.common.util;


import com.shop.clothing.category.CategoryDetailDto;
import com.shop.clothing.category.query.getAllCategoriesGroupByParentQuery.GetAllCategoriesGroupByParentQuery;
import com.shop.clothing.common.Cqrs.ISender;
import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
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

    @Caching(cacheable = {
            @Cacheable(value = "homePage", key = "'categories'"),
    })
    public List<CategoryDetailDto> getCategoryTree() {
        return sender.send(new GetAllCategoriesGroupByParentQuery()).get();
    }

    @AllArgsConstructor
    public static class Address {
        public String ward;
        public String district;
        public String city;
        public String detail;
    }
    public Address from(String address) {
        var addressList = address.split(",");
        var ward = addressList[addressList.length - 3].trim();
        var district = addressList[addressList.length - 2].trim();
        var city = addressList[addressList.length - 1].trim();
        // 0 to length - 3 is detail
        StringBuilder detail = new StringBuilder();
        for (int i = 0; i < addressList.length - 3; i++) {
            detail.append(addressList[i].trim()).append(", ");
        }
        return new Address(ward, district, city, detail.toString());
    }
}
