package com.shop.clothing.product.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.command.createColor.CreateColorCommand;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/color")
@AllArgsConstructor
public class ColorApiController {
    private final ISender sender;


    @PostMapping("/create")
    public ResponseEntity<Integer> createColor(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateColorCommand createColorCommand) {
        var result = sender.send(createColorCommand);
        return ResponseEntity.ok(result.orThrow());
    }
}
