package com.shop.clothing;

import com.shop.clothing.payment.momo.MomoService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.util.Assert;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class MomoTest {
    @Autowired
    private MomoService momoService;

    @Test
    public void testGetPaymentUrl() {
        assertDoesNotThrow(() -> {
            var url = momoService.createQRCodePayment(UUID.randomUUID().toString(), 1000000,"Thanh toán đơn hàng").getPayUrl();
            System.out.println(url);
        });
    }
}
