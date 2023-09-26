package com.shop.clothing;

import com.shop.clothing.entity.Brand;
import com.shop.clothing.repository.BrandRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.util.Assert;

@SpringBootTest
public class BrandRepositoryTest {
    private final BrandRepository brandRepository;
    @Autowired
    public BrandRepositoryTest(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testCreateBrand() {
        var brand = Brand.builder().
                name("Nike").
                slug("nike").
                build();
        brandRepository.save(brand);
        var expected = brandRepository.findById(brand.getBrandId());
        assert expected.isPresent();
        assert expected.get().getName().equals("Nike");
        assert expected.get().getCreatedBy().equals("admin");
        Assert.isNull(expected.get().getLastModifiedDate(), "Last modified date should be null");
    }
    @Test
    @WithMockUser(username = "editor", roles = {"EDITOR"})
    public  void editBrand() {
        var brand = Brand.builder().
                name("Nike2").
                slug("nike2").
                build();
        brandRepository.save(brand);
        var entity = brandRepository.findById(brand.getBrandId());
        assert entity.isPresent();
        entity.get().setName("Adidas");
        brandRepository.save(entity.get());
        var expected = brandRepository.findById(brand.getBrandId());
        assert expected.isPresent();
        Assert.isTrue(expected.get().getName().equals("Adidas"), "Name should be Adidas");
        Assert.isTrue(expected.get().getLastModifiedBy().equals("editor"), "Last modified by should be editor");

    }
    @Test
    @WithMockUser(username = "admin", roles = {"ADMIN"})
    public void testDeleteBrand() {
        var brand = Brand.builder().
                name("Nike3").
                slug("nike3").
                build();
        brandRepository.save(brand);
        var expected = brandRepository.findById(brand.getBrandId());
        assert expected.isPresent();
        brandRepository.deleteById(brand.getBrandId());
        var expected2 = brandRepository.findById(brand.getBrandId());
        assert expected2.isEmpty();

    }
}
