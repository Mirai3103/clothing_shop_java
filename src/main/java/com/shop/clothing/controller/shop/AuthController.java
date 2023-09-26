package com.shop.clothing.controller.shop;

import com.shop.clothing.request.auth.LoginRequest;
import com.shop.clothing.request.auth.RegisterRequest;
import com.shop.clothing.response.NotificationDto;
import com.shop.clothing.service.AuthService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.security.Principal;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerRequest", RegisterRequest.builder().build());
        return "register";
    }

    @GetMapping("/login")
    public String login(Model model, Authentication authentication) {
        model.addAttribute("loginRequest", LoginRequest.builder().build());
        if (authentication != null) {
         var redirectUrl="redirect:/?"+ NotificationDto.builder()
                 .title("Đăng nhập thành công")
                 .type("success")
                 .content("")
                 .build().toParams();
            System.out.println(redirectUrl);
            return redirectUrl;
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterRequest registerRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        var result = authService.register(registerRequest);
        if (result.isSuccess()) {
            model.addAttribute("notification", NotificationDto.builder()
                    .title("Đăng ký thành công")
                    .content("Vui lòng kiểm tra email để kích hoạt tài khoản")
                    .build());
            // redirect to login page
            return "redirect:/auth/login";
        }
        model.addAttribute("notification", NotificationDto.builder()
                .title("Đăng ký thất bại")
                .content(result.getError())
                .type("error")
                .build());
        return "register";
    }
}
