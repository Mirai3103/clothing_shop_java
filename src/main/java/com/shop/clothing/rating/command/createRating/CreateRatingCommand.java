package com.shop.clothing.rating.command.createRating;

import com.shop.clothing.common.Cqrs.IRequest;
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
