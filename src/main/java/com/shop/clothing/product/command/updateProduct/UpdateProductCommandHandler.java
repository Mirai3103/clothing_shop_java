package com.shop.clothing.product.command.updateProduct;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class UpdateProductCommandHandler implements IRequestHandler<UpdateProductCommand,Void> {
    @Override
    @Transactional
    public HandleResponse<Void> handle(UpdateProductCommand updateProductCommand) {
        return null;
    }
}
