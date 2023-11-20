package com.shop.clothing.order.command.cancelOrder;

import com.shop.clothing.common.Cqrs.IRequest;

public record CancelOrderCommand(String orderId) implements IRequest<Boolean> {

}
