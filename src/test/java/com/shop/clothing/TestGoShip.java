package com.shop.clothing;

import com.shop.clothing.delivery.IDeliveryService;
import com.shop.clothing.delivery.dto.CreateShipOrderRequest;
import com.shop.clothing.delivery.dto.GetValidShipServiceRequest;
import com.shop.clothing.delivery.goShip.GoShipAddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class TestGoShip {
    @Autowired
    private GoShipAddressService goShipAddressService;
    @Autowired
    private IDeliveryService deliveryService;

    @Test
    public void shouldNotThrowExceptionWhenFindProvinceId() {
        Assertions.assertDoesNotThrow(() -> {
            System.out.println(goShipAddressService.findProvinceId("Hồ chí Minh"));
        });
    }

    @Test
    public void shouldNotThrowExceptionWhenFindDistrictId() {
        Assertions.assertDoesNotThrow(() -> {
            GetValidShipServiceRequest request = GetValidShipServiceRequest.builder()
                    .cod(0)
                    .fromDistrict("Quận 1")
                    .toDistrict("Nghĩa Hành")
                    .fromCity("Hồ chí Minh")
                    .toCity("Quảng Ngãi")
                    .orderValue(200000)
                    .build();
            var result = deliveryService.getValidShipService(request);
            Assertions.assertNotNull(result);
            Assertions.assertNotEquals(0, result.size());
        });
    }
    @Test
    public void shouldNotThrowExceptionWhenCreateOrder() {

        Assertions.assertDoesNotThrow(() -> {
            GetValidShipServiceRequest getValidShipServiceRequest = GetValidShipServiceRequest.builder()
                    .cod(0)
                    .fromDistrict("Quận 1")
                    .toDistrict("Nghĩa Hành")
                    .fromCity("Hồ chí Minh")
                    .toCity("Quảng Ngãi")
                    .orderValue(200000)
                    .build();
            var result = deliveryService.getValidShipService(getValidShipServiceRequest);
            Assertions.assertNotNull(result);
            Assertions.assertNotEquals(0, result.size());
            CreateShipOrderRequest createShipOrderRequest= CreateShipOrderRequest.builder()
                    .rateServiceId(result.get(0).getId())
                    .toName("Nguyễn Văn A")
                    .toPhone("0123456789")
                    .toAddress("Tình Phú Bắc, Xã Hành Minh, Huyện Nghĩa Hành, Tỉnh Quảng Ngãi")
                    .orderAmount(200000)
                    .build();
            var response = deliveryService.createOrder(createShipOrderRequest);
            Assertions.assertNotNull(response);

        });
    }
}
