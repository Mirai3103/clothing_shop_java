package com.shop.clothing.controller.shop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
@Controller
public class Home {
    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
