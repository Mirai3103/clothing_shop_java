package com.shop.clothing.product.endpoint;


import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.command.createProductOption.CreateProductOptionCommand;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/product-option")
@AllArgsConstructor
public class ProductOptionApiController {
    private final ISender sender;

    @Secured("CREATE_PRODUCT")
    @PostMapping("/create")
    public ResponseEntity<Integer> createProductOption(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductOptionCommand createProductOptionCommand) {
        var result = sender.send(createProductOptionCommand);
        return ResponseEntity.ok(result.orThrow());
    }
    @Secured("DELETE_PRODUCT")

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProductOption(@PathVariable @Parameter(in = ParameterIn.PATH, name = "id") int id) {
        var result = sender.send(new com.shop.clothing.product.command.deleteProductOption.DeleteProductOptionCommand(id));
        return ResponseEntity.noContent().build();
    }
}
