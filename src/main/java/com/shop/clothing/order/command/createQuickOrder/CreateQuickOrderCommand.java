package com.shop.clothing.order.command.createQuickOrder;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.validation.NullOrNotBlank;
import com.shop.clothing.payment.entity.enums.PaymentMethod;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Setter
public class CreateQuickOrderCommand implements IRequest<String> {
    @Min(value = 1, message = "Số lượng sản phẩm phải lớn hơn 0")
    public int quantity;
    @Min(value = 1, message = "Cần chọn sản phẩm")
    public int productOptionId;
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
