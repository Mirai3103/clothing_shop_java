package com.shop.clothing.common.util;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class BeanUtil {

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    public ClientUtil clientUtil() {
        return new ClientUtil();
    }
    @Bean
    public SlugUtil slugUtil() {
        return new SlugUtil();
    }
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
