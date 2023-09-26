package com.shop.clothing.controller.api;


import com.shop.clothing.entity.Brand;
import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.response.Paginated;
import com.shop.clothing.service.BrandService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/brand")
public class BrandApiController {
    private final BrandService brandService;

    @GetMapping()
    public Paginated<Brand> getBrands(@Valid PaginationRequest paginationRequest) {
        var rs= brandService.getAllBrands(paginationRequest);
        return Paginated.of(rs);
    }
}
