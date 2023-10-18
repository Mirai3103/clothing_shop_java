package com.shop.clothing.controller.shop;

import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.config.ICurrentUserService;
import com.shop.clothing.order.command.createOrder.CreateOrderCommand;
import com.shop.clothing.order.command.createQuickOrder.CreateQuickOrderCommand;
import com.shop.clothing.product.query.getProductOptionById.GetProductOptionByIdQuery;
import com.shop.clothing.product.query.getProductOptionsByListId.GetProductOptionsByListIdQuery;
import com.shop.clothing.user.entity.User;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping("/order")
@Controller
@AllArgsConstructor
public class ShopOrderController {

    private final ICurrentUserService currentUserService;
    private final ISender sender;

    public static class OrderItem {
        @NotEmpty(message = "Không được để trống")
        public List<Integer> productOptionId;
    }

    @PostMapping("")
    @Secured("ROLE_USER")
    public String createOrder(@Valid OrderItem orderItem, Model model) {
        var createOrderCommand = new CreateOrderCommand();
        createOrderCommand.setProductOptionIds(orderItem.productOptionId);
        User currentUser = (User) currentUserService.getCurrentUser().get();
        createOrderCommand.setAddress(currentUser.getAddress());
        createOrderCommand.setCustomerName(currentUser.getFirstName() + " " + currentUser.getLastName());
        createOrderCommand.setPhoneNumber(currentUser.getPhoneNumber());
        var productOption = sender.send(new GetProductOptionsByListIdQuery(orderItem.productOptionId)).get();
        model.addAttribute("productOptions", productOption);
        model.addAttribute("command", createOrderCommand);

        return "index";
    }

    @PostMapping("/create")
    @Secured("ROLE_USER")
    public String createOrder(@Valid CreateOrderCommand command) {
//        var response = sender.send(command);
//        if(response.isOk()){
//            return "redirect:/order/success";
//        }
        return "redirect:/home";
    }


    @GetMapping("/quick")
    public String createQuickOrder(@RequestParam("productOptionId") int productOptionId, @RequestParam("quantity") int quantity, Model model) {
        var createOrderCommand = new CreateQuickOrderCommand();
        createOrderCommand.setQuantity(quantity);
        createOrderCommand.setProductOptionId(productOptionId);
        if (currentUserService.getCurrentUser().isPresent()) {
            User currentUser = (User) currentUserService.getCurrentUser().get();
            createOrderCommand.setAddress(currentUser.getAddress());
            createOrderCommand.setCustomerName(currentUser.getFirstName() + " " + currentUser.getLastName());
            createOrderCommand.setPhoneNumber(currentUser.getPhoneNumber());
        }
        var productOption = sender.send(new GetProductOptionByIdQuery(productOptionId)).get();
        model.addAttribute("productOption", productOption);
        model.addAttribute("command", createOrderCommand);
        return "order/quick";
    }
}

