package com.shop.clothing.order.command.createOrder;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.validation.NullOrNotBlank;
import com.shop.clothing.payment.entity.enums.PaymentMethod;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CreateOrderCommand implements IRequest<String> {
    @NotEmpty(message = "Cần chọn sản phẩm")
    public List<Integer> productOptionIds;
    @NotEmpty(message = "Cần nhập tên khách hàng")
    private String customerName;
    @NotEmpty(message = "Cần nhập địa chỉ")
    private String address;
    @NotEmpty(message = "Cần nhập số điện thoại")
    private String phoneNumber;
    @NotEmpty(message = "Cần nhập email")
    private String note;
    @NullOrNotBlank(message = "Cần nhập mã giảm giá")
    private String promotionCode;
    @NotNull(message = "Phương thức thanh toán không được để trống")
    private PaymentMethod paymentMethod = PaymentMethod.COD;
}
