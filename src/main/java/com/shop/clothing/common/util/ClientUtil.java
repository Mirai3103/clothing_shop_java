package com.shop.clothing.common.util;


import lombok.AllArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Component;


public class ClientUtil {
    public String toJsonString(Object object) {
        return new JSONObject(object).toString();
    }
}
