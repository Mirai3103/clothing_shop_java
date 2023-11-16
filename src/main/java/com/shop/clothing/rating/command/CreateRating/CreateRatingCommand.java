package com.shop.clothing.rating.command.CreateRating;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.product.dto.ProductOptionDto;
import com.shop.clothing.user.UserBriefDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateRatingCommand implements IRequest<Integer>
{
    private int id;
    private String content;
    private int value;
    private int productOptionId;
    private String orderId;
}
