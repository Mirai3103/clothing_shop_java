package com.shop.clothing.promotion.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.promotion.PromotionDto;
import com.shop.clothing.promotion.Query.checkPromotion.CheckPromotionQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/promotion")
@AllArgsConstructor

public class PromotionController {
    private final ISender sender;
    @GetMapping("/check")
    public ResponseEntity<PromotionDto> checkPromotion(@Valid @ParameterObject CheckPromotionQuery checkPromotionQuery) {
       var result = sender.send(checkPromotionQuery);
         return ResponseEntity.ok(result.orThrow());
    }
}
