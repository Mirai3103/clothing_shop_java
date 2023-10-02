package com.shop.clothing.delivery.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.delivery.query.getDeliveryFee.GetDeliveryFeeQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/delivery")
@AllArgsConstructor
public class DeliveryController {
    private final ISender sender;

    @PostMapping("/fee")
    public ResponseEntity<Integer> getDeliveryFee(@Valid @RequestBody GetDeliveryFeeQuery getDeliveryFeeQuery) {
        var result = sender.send(getDeliveryFeeQuery);
        return ResponseEntity.ok(result.orThrow());
    }
}
