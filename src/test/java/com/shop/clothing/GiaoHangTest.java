package com.shop.clothing;

import com.shop.clothing.delivery.GiaoHangNhanhService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;


@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class GiaoHangTest {
    @Autowired
    private GiaoHangNhanhService giaoHangNhanhService;

    @Test
    public void testGetProvince() {
        var provinceId = giaoHangNhanhService.findProvinceId("Hồ Chí Minh");
        Assert.isTrue(provinceId > 0, "Province id must be greater than 0");
        Assert.isTrue(provinceId == 202, "Ho Chi Minh province id must be 202");
    }
    @Test void testGetDistrict() {
        var districtId = giaoHangNhanhService.findDistrictId("Quận 1", 202);
        Assert.isTrue(districtId > 0, "District id must be greater than 0");
        System.out.println(districtId);
    }
    @Test void testGetWard() {
        var wardId = giaoHangNhanhService.findWardId("Bến Nghé", 1442);
        System.out.println(wardId);
        Assert.isTrue(wardId > 0, "Ward id must be greater than 0");
    }
}
