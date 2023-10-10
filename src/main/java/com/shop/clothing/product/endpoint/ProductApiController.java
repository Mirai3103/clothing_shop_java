package com.shop.clothing.product.endpoint;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.command.createProduct.CreateProductCommand;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.dto.ProductDetailDto;
import com.shop.clothing.product.entity.Product;
import com.shop.clothing.product.query.getAllProducts.GetAllProductsQuery;
import com.shop.clothing.product.query.getProductById.GetProductByIdQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/product")
@AllArgsConstructor
public class ProductApiController {
    private final ISender sender;

    @GetMapping("/")
    public ResponseEntity<Paginated<ProductBriefDto>> getProducts(@Valid @ParameterObject GetAllProductsQuery getAllProductsQuery) {
     
        var result = sender.send(getAllProductsQuery);
        return ResponseEntity.ok(result.orThrow());
    }
    @PostMapping("/create")
    public ResponseEntity<Integer> createProduct(@RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody @Valid CreateProductCommand createProductCommand) {
        var result = sender.send(createProductCommand);
        return ResponseEntity.ok(result.orThrow());
    }
    @GetMapping("/{productId}")
    public ResponseEntity<ProductDetailDto>getProductById(@PathVariable int productId) {
        var result = sender.send(new GetProductByIdQuery(productId));
        return ResponseEntity.ok(result.orThrow());
    }
}
