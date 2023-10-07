package com.shop.clothing.payment.command.createPayment;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.payment.dto.CreatePaymentResponse;
import com.shop.clothing.payment.entity.enums.PaymentMethod;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreatePaymentCommand implements IRequest<CreatePaymentResponse> {
    private String orderId;
      private PaymentMethod paymentMethod = PaymentMethod.COD;

}
