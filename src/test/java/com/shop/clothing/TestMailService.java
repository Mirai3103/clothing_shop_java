package com.shop.clothing;

import com.shop.clothing.mail.MailService;
import lombok.AllArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class TestMailService {
    @Autowired
    private  MailService mailService;
    @Test
    public void testSendEmail() {
        assertDoesNotThrow(() -> {
            mailService.sendEmail("huuhoag1412@outlook.com", "huuhoag1412@gmail.com", "Test", "Test");
        });
    }
}
