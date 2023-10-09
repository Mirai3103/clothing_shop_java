package com.shop.clothing;

import com.shop.clothing.auth.JWT.JwtConfig;
import com.shop.clothing.auth.JWT.JwtService;
import io.jsonwebtoken.ExpiredJwtException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.security.SignatureException;
import java.util.Map;

public class JwtTest {
    private static JwtService jwtService;
    @BeforeAll
    public static void setUp() {
        var jwtConfig = new JwtConfig("secretszdddddddddddddddddzzzzzzzzzzzzdafsfsdfsdfsdfsdfsdfdasd");
        jwtService = new JwtService(jwtConfig);

    }
    @Test
    public void shouldCreateToken(){
        assertDoesNotThrow(()->{
            var token = jwtService.generateToken(Map.of("id", 1), 5*60*1000);
            System.out.println(token);
            var id = jwtService.getValue(token, (c)->c.get("id"));
            assertEquals(id, 1);
        });
    }
    @Test void shouldInvalidToken(){
        assertThrows(SignatureException.class, ()->{
            var token = "eyJhbGciOiJIUzM4NCJ9.eyJpZCI6MSwiaWF0IjoxNjk2NzU4OTQ5LCJleHAiOjE2OTY3NTkyNDl9.r20ZwUGAqfEJM5xFGJeMI6JC-JH3ygItRhLcyIuVi5bB3ZJ0owDlHCwyX2KuOGme";
            var id = jwtService.getValue(token, (c)->c.get("id"));
        });
    }
    @Test void shouldExpiredToken(){
        assertThrows(ExpiredJwtException.class, ()->{
            var token = "eyJhbGciOiJIUzM4NCJ9.eyJpZCI6MSwiaWF0IjoxNjk2NzYwMjM2LCJleHAiOjE2OTY3NjA1MzZ9.8LMnvGqsPlK0w9IIUI30PyLz-KjzR9AWrNNBFz5d4TyXeXq1SMtCEZ8eX6Qc_Y3P";
        });
    }
}
