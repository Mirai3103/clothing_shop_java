package com.shop.clothing.controller.shop;

import com.shop.clothing.auth.commands.login.LoginRequest;
import com.shop.clothing.auth.commands.register.RegisterCommand;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.common.dto.NotificationDto;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private final ISender sender;

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("registerCommand", RegisterCommand.builder().build());
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

            return redirectUrl;
        }
        return "login";
    }

    @PostMapping("/register")
    public String register(@Valid RegisterCommand registerCommand, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        var result = sender.send(registerCommand);
        if (result.isOk()) {
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
