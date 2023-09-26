package com.shop.clothing.service;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Brand;
import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.request.brand.CreateBrandRequest;
import com.shop.clothing.request.brand.UpdateBrandRequest;
import org.springframework.data.domain.Page;

import java.util.Optional;

public interface BrandService {
    Result<Brand> createBrand(CreateBrandRequest createBrandRequest);
    Result<Brand> updateBrand(UpdateBrandRequest updateBrandRequest);
    Optional<Brand> getBrandById(int id);


    Page<Brand> getAllBrands(PaginationRequest paginationRequest);

    Result<Brand> deleteBrandById(int id);


}
