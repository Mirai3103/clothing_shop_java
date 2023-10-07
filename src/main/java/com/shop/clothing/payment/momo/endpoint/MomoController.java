package com.shop.clothing.payment.momo.endpoint;

import com.shop.clothing.payment.momo.MomoCallbackParam;
import com.shop.clothing.payment.momo.MomoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/momo")
@AllArgsConstructor
public class MomoController {
    private final MomoService momoService;

    @GetMapping("/callback")
    public String callback(MomoCallbackParam param) {
        return momoService.handleCallback(param).name();

    }

    @PostMapping("/ipn")
    public ResponseEntity<Void> ipn(MomoCallbackParam param) {
        System.out.println(param);
        return ResponseEntity.noContent().build();
    }
}