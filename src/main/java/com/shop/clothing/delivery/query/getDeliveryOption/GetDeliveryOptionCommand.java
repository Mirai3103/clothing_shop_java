package com.shop.clothing.delivery.query.getDeliveryOption;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.delivery.config.GiaoHangNhanhConfig;
import lombok.*;

@AllArgsConstructor
@Builder
@NoArgsConstructor
@Getter
@Setter
public class GetDeliveryOptionCommand  implements IRequest<GetDeliveryOptionResult> {
    private String toDistrict;
}
