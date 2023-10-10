package com.shop.clothing.controller.shop;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.user.query.getMyProfile.GetMyProfileQuery;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@AllArgsConstructor
@RequestMapping("/user")
@PreAuthorize("isAuthenticated()")
public class UserController {
    private final ISender _sender;

    @GetMapping("my-account")
    public ModelAndView myProfile() {
        var result = _sender.send(new GetMyProfileQuery());
        if (result.hasError()) {
            return new ModelAndView("redirect:/auth/login");
        }
        return new ModelAndView("user/my-account", "user", result.get());
    }
}
