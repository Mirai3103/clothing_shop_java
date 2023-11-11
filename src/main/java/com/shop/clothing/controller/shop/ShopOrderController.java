package com.shop.clothing.controller.shop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.shop.clothing.common.Cqrs.ISender;
import com.shop.clothing.config.ICurrentUserService;
import com.shop.clothing.order.command.createOrder.CreateOrderCommand;
import com.shop.clothing.order.entity.Order;
import com.shop.clothing.payment.command.createPayment.CreatePaymentCommand;
import com.shop.clothing.product.query.getProductOptionsByListId.GetProductOptionsByListIdQuery;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RequestMapping("/order")
@Controller
@AllArgsConstructor
public class ShopOrderController {

    private final ISender sender;
    private ObjectMapper objectMapper;


    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('CAN_ORDER') or isAnonymous()")
    public String createOrder(@RequestParam String orderItemsJson, Model model) {
        List<CreateOrderCommand.OrderItem> orderItems = null;
        try {
            orderItems = objectMapper.readValue(orderItemsJson, new TypeReference<List<CreateOrderCommand.OrderItem>>() {
            });
        } catch (Exception e) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Lỗi định dạng dữ liệu");
        }
        if (orderItems == null || orderItems.isEmpty()) {
            throw new ResponseStatusException(org.springframework.http.HttpStatus.BAD_REQUEST, "Cần chọn sản phẩm");
        }

        var createOrderCommand = new CreateOrderCommand();
        var productOption = sender.send(new GetProductOptionsByListIdQuery(orderItems.stream().map(CreateOrderCommand.OrderItem::getProductOptionId).toList())).get();
        createOrderCommand.setOrderItems(orderItems);

        OrderModel orderModel = new OrderModel();
        orderModel.setOrderItems(productOption, orderItems);
        model.addAttribute("orderModel", orderModel);
        model.addAttribute("command", createOrderCommand);
        return "order/index";
    }

    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('CAN_ORDER') or isAnonymous()")
    public String createOrder(@Valid CreateOrderCommand command, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "order/index";
        }
        var response = sender.send(command);
        if (response.isOk()) {
            var createPaymentCommand = new CreatePaymentCommand();
            createPaymentCommand.setOrderId(response.get());
            var paymentResponse = sender.send(createPaymentCommand);
            if (paymentResponse.isOk() && paymentResponse.get().isRedirect()) {
                return "redirect:" + paymentResponse.get().getRedirectUrl();
            }
            model.addAttribute("message", "Đơn hàng #" + response.get() + " đã được tạo thành công");
            model.addAttribute("header", "Đặt hàng thành công");
            model.addAttribute("subMessage", "Cảm ơn bạn đã mua hàng tại shop");
            model.addAttribute("detailUrl", "/order/" + response.get());
            return "order/success";
        }
        return "order/index";
    }
}

