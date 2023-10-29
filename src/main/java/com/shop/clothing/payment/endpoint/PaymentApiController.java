package com.shop.clothing.payment.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.payment.command.createPayment.CreatePaymentCommand;
import com.shop.clothing.payment.dto.CreatePaymentResponse;
import com.shop.clothing.payment.momo.MomoService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payment")
@AllArgsConstructor

public class PaymentApiController {
    private final ISender sender;

    @PostMapping("/create")
    @Secured("ROLE_USER")
    public ResponseEntity<CreatePaymentResponse> createPayment(@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreatePaymentCommand command) {
        return ResponseEntity.ok(sender.send(command).orThrow());
    }


}
