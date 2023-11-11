package com.shop.clothing.controller.admin;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.stockReceipt.query.getAllSuppliers.GetAllSuppliersQuery;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@AllArgsConstructor
@RequestMapping("/admin/stock-receipt")
public class StockReceiptController {
    private final ISender sender;

    @GetMapping ()
    public String index(Model model){
        var query = new GetAllSuppliersQuery();
        query.setPage(1);
        query.setPageSize(100);
        query.setSortField("name");
        query.setSortDir("asc");
        var listSupplier = sender.send(query).get();
        model.addAttribute("suppliers", listSupplier.getData());
        return "admin/stockReceipt/index";
    }
}
