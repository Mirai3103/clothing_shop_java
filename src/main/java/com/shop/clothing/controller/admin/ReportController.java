package com.shop.clothing.controller.admin;

import com.shop.clothing.common.Cqrs.ISender;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/report")
public class ReportController {
    private  final ISender sender;

    @GetMapping("/revenue")
    public String getRevenueReport(){
        return "admin/report/revenue";
    }
}
