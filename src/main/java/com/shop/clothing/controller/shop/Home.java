package com.shop.clothing.controller.shop;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.product.query.getAllProducts.GetAllProductsQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/")
@Controller
@AllArgsConstructor
public class Home {
    private final ISender sender;

    @GetMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @GetMapping("/test")
    public String test() {
        return "order/quick";
    }

    @GetMapping("/home")
    public String home(Model model) {
        var newProductQuery = new GetAllProductsQuery();
        newProductQuery.setSortField("id");
        newProductQuery.setSortDir("desc");
        newProductQuery.setPage(1);
        newProductQuery.setPageSize(10);
        var hotProductQuery = new GetAllProductsQuery();
        hotProductQuery.setSortField("name");
        hotProductQuery.setSortDir("desc");
        hotProductQuery.setPage(1);
        hotProductQuery.setPageSize(10);
        var newProducts = sender.send(newProductQuery).get();
        var hotProducts = sender.send(hotProductQuery).get();
        model.addAttribute("newProducts", newProducts.getData());
        model.addAttribute("hotProducts", hotProducts.getData());
        return "index";
    }
}
