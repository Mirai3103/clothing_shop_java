package com.shop.clothing.payment.momo.endpoint;

import com.shop.clothing.payment.momo.MomoCallbackParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/momo")
public class MomoController {

    @GetMapping("/callback")
    public String callback(MomoCallbackParam param) {
        System.out.println(param);
        return "OK";
    }

    @PostMapping("/ipn")
    public ResponseEntity<Void> ipn(MomoCallbackParam param) {
        System.out.println(param);
        return ResponseEntity.noContent().build();
    }
}