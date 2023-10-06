package com.shop.clothing.product.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.command.createProductOption.CreateProductOptionCommand;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product-image")
@AllArgsConstructor
public class ProductImageApiController {
    private final ISender sender;

    @PostMapping("/create")
    public ResponseEntity<Void> createProduct(@RequestBody @ParameterObject @Valid CreateProductOptionCommand createProductOptionCommand) {
        var result = sender.send(createProductOptionCommand);
        return ResponseEntity.ok().build();
    }
}
