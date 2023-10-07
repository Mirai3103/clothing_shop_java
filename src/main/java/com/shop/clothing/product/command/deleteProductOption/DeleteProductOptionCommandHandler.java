package com.shop.clothing.product.command.deleteProductOption;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.product.entity.ProductOption;
import com.shop.clothing.product.repository.ProductOptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeleteProductOptionCommandHandler implements IRequestHandler<DeleteProductOptionCommand, Void> {
    private final ProductOptionRepository productOptionRepository;

    @Override
    @Transactional
    public HandleResponse<Void> handle(DeleteProductOptionCommand deleteProductOptionCommand) {
        var productOption = productOptionRepository.findById(deleteProductOptionCommand.id());
        if (productOption.isEmpty()) {
            return HandleResponse.error("Mẫu sản phẩm không tồn tại");
        }

        productOptionRepository.delete(productOption.get());
        return HandleResponse.ok();
    }
}
