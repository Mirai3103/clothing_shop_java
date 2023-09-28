package com.shop.clothing.config;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.ArrayList;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
@EnableWebMvc

public class SecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.ALWAYS));
        http.getSharedObject(AuthenticationManagerBuilder.class).authenticationProvider(daoAuthenticationProvider());

        http.authorizeHttpRequests(authConfig -> {
            authConfig.requestMatchers(HttpMethod.GET, "/admin/brand/**").hasAuthority("MANAGE_BRANDS");
            authConfig.requestMatchers(HttpMethod.GET, "/admin/category/**").hasAuthority("MANAGE_CATEGORIES");
            authConfig.requestMatchers(HttpMethod.GET, "/admin/**").hasAnyRole("ADMIN_DASHBOARD");
            authConfig.anyRequest().permitAll();

        }).rememberMe(rememberMe -> {
            rememberMe.key("remember-me");
            rememberMe.tokenValiditySeconds(7 * 24 * 60 * 60); // 7 days
        }).formLogin(login -> {
            login.loginPage("/auth/login");
            login.failureUrl("/auth/login?error=true");
            login.defaultSuccessUrl("/");
        }).logout(logout -> {
            logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"));
            logout.logoutSuccessUrl("/");
            logout.deleteCookies("JSESSIONID");
            logout.invalidateHttpSession(true);
        }).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(this.userDetailsService);
        return provider;
    }


}
