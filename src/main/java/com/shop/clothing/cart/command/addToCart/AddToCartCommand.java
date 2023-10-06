package com.shop.clothing.cart.command.addToCart;

import com.shop.clothing.common.Cqrs.IRequest;

public record AddToCardCommand(int productId, int quantity) implements IRequest<Void> {

}
