package com.shop.clothing.controller.shop;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@RequestMapping("/")
public class Home {
    @GetMapping("/")
    public ModelAndView index() {
        return new ModelAndView("redirect:/home");
    }
    @GetMapping("/home")
    public String home() {
        return "index";
    }
}
