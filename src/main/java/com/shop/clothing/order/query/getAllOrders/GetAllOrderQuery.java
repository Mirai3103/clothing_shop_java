package com.shop.clothing.order.query.getAllOrders;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.order.dto.OrderBriefDto;
import com.shop.clothing.order.dto.OrderDto;
import com.shop.clothing.order.entity.enums.OrderStatus;
import com.shop.clothing.payment.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
public class GetAllOrderQuery extends PaginationRequest implements IRequest<Paginated<OrderBriefDto>> {
    private PaymentStatus paymentStatus;
    private Date startDate;
    private Date endDate;
    private OrderStatus orderStatus;
    private int amountFrom = 0;
    private int amountTo = Integer.MAX_VALUE;
}
