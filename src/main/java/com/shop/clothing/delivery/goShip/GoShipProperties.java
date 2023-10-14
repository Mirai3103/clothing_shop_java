package com.shop.clothing.delivery.goShip;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
@Getter
public class GoShipProperties {
    @Value("${go-ship.client-id}")
    private String clientId;
    @Value("${go-ship.client-secret}")
    private String clientSecret;
    @Value("${go-ship.access-token}")
    private String accessToken;
    @Value("${go-ship.endpoint}")
    private String endpoint;
    @Value("${go-ship.shop-name}")
    private String shopName;
    @Value("${go-ship.shop-phone}")
    private String shopPhone;
    @Value("${go-ship.shop-street}")
    private String shopStreet;
    @Value("${go-ship.shop-ward}")
    private String shopWard;
    @Value("${go-ship.shop-district}")
    private String shopDistrict;
    @Value("${go-ship.shop-city}")
    private String shopCity;
    public String unicodeEscapeToUTF8(String s) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '\\' && s.charAt(i + 1) == 'u') {
                String tmp = s.substring(i + 2, i + 6);
                int code = Integer.parseInt(tmp, 16);
                sb.append((char) code);
                i += 5;
            } else {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    public String getShopStreet() {
        return unicodeEscapeToUTF8(shopStreet);
    }
    public String getShopWard() {
        return unicodeEscapeToUTF8(shopWard);
    }
    public String getShopDistrict() {
        return unicodeEscapeToUTF8(shopDistrict);
    }
    public String getShopCity() {
        return unicodeEscapeToUTF8(shopCity);
    }
    public String getShopName() {
        return unicodeEscapeToUTF8(shopName);
    }

}
