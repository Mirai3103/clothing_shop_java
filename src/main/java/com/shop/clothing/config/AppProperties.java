package com.shop.clothing.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class AppProperties {
    @Value("${app.host}")
    private String host ;
    @Value("${app.owner.email}")
    private String ownerEmail;
    @Value("${app.owner.name}")
    private String ownerName;


}
