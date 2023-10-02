package com.shop.clothing.delivery.query.getDeliveryOption;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.delivery.GiaoHangNhanhService;
import com.shop.clothing.delivery.config.GiaoHangNhanhConfig;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class GetDeliveryOptionCommandHandler implements IRequestHandler<GetDeliveryOptionCommand, GetDeliveryOptionResult> {
    private final GiaoHangNhanhConfig giaoHangNhanhConfig;
    private final GiaoHangNhanhService giaoHangNhanhService;

    @Override
    public HandleResponse<GetDeliveryOptionResult> handle(GetDeliveryOptionCommand getDeliveryOptionCommand) {
        return null;
    }
}
