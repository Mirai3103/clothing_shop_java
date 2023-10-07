package com.shop.clothing.order.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.order.command.createOrder.CreateOrderCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderApiController {
    private final ISender sender;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder(@Valid @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody CreateOrderCommand createOrderCommand) {
        var result = sender.send(createOrderCommand);
        return ResponseEntity.ok(result.orThrow());
    }
}
