package com.shop.clothing.common.util;


import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


public class ClientUtil {
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
}
