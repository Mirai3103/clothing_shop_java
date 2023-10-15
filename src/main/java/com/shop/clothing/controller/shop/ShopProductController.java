package com.shop.clothing.controller.shop;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.dto.ProductDetailDto;
import com.shop.clothing.product.query.getProductById.GetProductByIdQuery;
import com.shop.clothing.product.query.getProductBySlug.GetProductBySlugQuery;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

@Controller
@AllArgsConstructor
@RequestMapping("/product")
public class ShopProductController {
    private final ISender sender;
    @GetMapping("{slug}")
    public String productDetail(Model model, @PathVariable String slug) {
        var regex = "[0-9]+";
        com.shop.clothing.common.Cqrs.HandleResponse<ProductDetailDto> result;
        if (slug.matches(regex)) {
            result = sender.send(new GetProductByIdQuery(Integer.parseInt(slug)));
        } else {
            result = sender.send(new GetProductBySlugQuery(slug));
        }
        if (result.hasError()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Không tìm thấy sản phẩm");
        }

        model.addAttribute("product", result.get());

        return "productDetail";
    }
}
