package com.shop.clothing.product.endpoint;


import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.command.createProductOption.CreateProductOptionCommand;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product-option")
@AllArgsConstructor
public class ProductOptionApiController {
    private final ISender sender;

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductOptionCommand createProductOptionCommand) {
        var result = sender.send(createProductOptionCommand);
        return ResponseEntity.ok().build();
    }
}
