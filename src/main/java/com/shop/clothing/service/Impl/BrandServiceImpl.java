package com.shop.clothing.service.Impl;

import com.shop.clothing.common.Result;
import com.shop.clothing.entity.Brand;
import com.shop.clothing.repository.BrandRepository;
import com.shop.clothing.request.PaginationRequest;
import com.shop.clothing.request.brand.CreateBrandRequest;
import com.shop.clothing.request.brand.UpdateBrandRequest;
import com.shop.clothing.service.BrandService;
import com.shop.clothing.util.SlugUtil;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class BrandServiceImpl implements BrandService {
    private final BrandRepository brandRepository;
    private final SlugUtil slugUtil;

    @Override
    public Result<Brand> createBrand(CreateBrandRequest createBrandRequest) {
        var existingBrand = brandRepository.findByName(createBrandRequest.getName());
        if (existingBrand.isPresent()) {
            return Result.error("Brand with name: " + createBrandRequest.getName() + " already exists");
        }
        var brand = new Brand();
        brand.setName(createBrandRequest.getName());
        brand.setSlug(slugUtil.slugify(createBrandRequest.getName()));
        brandRepository.save(brand);
        return Result.success(brand);
    }

    @Override
    public Result<Brand> updateBrand(UpdateBrandRequest updateBrandRequest) {
        var existingBrand = brandRepository.findById(updateBrandRequest.getId());
        if (existingBrand.isEmpty()) {
            return Result.error("Brand with id: " + updateBrandRequest.getId() + " does not exist");
        }
        var brand = existingBrand.get();
        brand.setName(updateBrandRequest.getName());
        brand.setSlug(slugUtil.slugify(updateBrandRequest.getName()));
        brandRepository.save(brand);
        return Result.success(brand);
    }

    @Override
    public Optional<Brand> getBrandById(int id) {
        return brandRepository.findById(id);
    }

    @Override
    public Page<Brand> getAllBrands(PaginationRequest paginationRequest) {
        // sort by name asc
        var sort = Sort.by(paginationRequest.getSortDirection(), paginationRequest.getSortField().isEmpty() ? "name" : paginationRequest.getSortField());
        var pageRequest = PageRequest.of(paginationRequest.getPage() - 1, paginationRequest.getSize(), sort);
        if (!paginationRequest.getKeyword().isBlank()) {
            return brandRepository.findAllByNameContaining(paginationRequest.getKeyword(), pageRequest);
        }
        return brandRepository.findAll(pageRequest);
    }

    @Override
    public Result<Brand> deleteBrandById(int id) {
        var existingBrand = brandRepository.findById(id);
        if (existingBrand.isEmpty()) {
            return Result.error("Brand with id: " + id + " does not exist");
        }
        brandRepository.deleteById(id);
        return Result.success(existingBrand.get());
    }
}
