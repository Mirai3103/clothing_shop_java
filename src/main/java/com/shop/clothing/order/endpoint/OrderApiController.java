package com.shop.clothing.order.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.order.command.createOrder.CreateOrderCommand;
import com.shop.clothing.order.dto.OrderBriefDto;
import com.shop.clothing.order.query.getAllOrders.GetAllOrderQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('CREATE_ORDER','UPDATE_ORDER','VIEW_ORDER')")
    public ResponseEntity<Paginated<OrderBriefDto>> getAllOrders(@Valid @ParameterObject GetAllOrderQuery getAllOrderQuery) {
        return ResponseEntity.ok(sender.send(getAllOrderQuery).orThrow());
    }

}
