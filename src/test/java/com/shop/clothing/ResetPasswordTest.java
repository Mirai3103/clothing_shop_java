package com.shop.clothing;

import com.shop.clothing.auth.commands.forgotPassword.ForgotPasswordCommand;
import com.shop.clothing.auth.commands.resetPassword.ResetPasswordCommand;
import com.shop.clothing.common.Cqrs.ISender;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = ClothingApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(
        locations = "classpath:application.properties")
@ExtendWith(SpringExtension.class)
public class ResetPasswordTest {
    @Autowired
    private ISender sender;

    @Test
    public void testForgotPassword() {
        assertDoesNotThrow(() -> {
            var result = sender.send(new ForgotPasswordCommand("huuhoag1412@gmail.com"));
            result.orThrow();
        });
    }

    @Test
    public void testReset() {
        var resetCommand = new ResetPasswordCommand();
        resetCommand.setToken("eyJhbGciOiJIUzM4NCJ9.eyJ1c2VySWQiOiIwNDc4NzA0MS01MjRkLTQyODctYjA0MS1hMDZhMzE0MmJiYTgiLCJlbWFpbCI6Imh1dWhvYWcxNDEyQGdtYWlsLmNvbSIsImlhdCI6MTY5Njc2NTM4MSwiZXhwIjoxNjk2NzY2MjgxLCJpc3MiOiJzaG9wLWNsb3RoaW5nIn0.3NOSsJ0inTE4xBfQKAjRPs9xPhzMJ1FljtfFiyug861flbrWFvLaeiI9AdR9RJQy");
        resetCommand.setNewPassword("000000");
        assertDoesNotThrow(() -> {
            var result = sender.send(resetCommand);
            result.orThrow();
        });
    }
}
