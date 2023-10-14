package com.shop.clothing.product.command.deleteProductOption;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.order.repository.OrderItemRepository;
import com.shop.clothing.order.repository.OrderRepository;
import com.shop.clothing.product.entity.ProductOption;
import com.shop.clothing.product.repository.ProductOptionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class DeleteProductOptionCommandHandler implements IRequestHandler<DeleteProductOptionCommand, Void> {
    private final ProductOptionRepository productOptionRepository;
private final OrderItemRepository orderItemRepository;
    @Override
    @Transactional
    public HandleResponse<Void> handle(DeleteProductOptionCommand deleteProductOptionCommand) {
        var productOption = productOptionRepository.findById(deleteProductOptionCommand.id());
        if (productOption.isEmpty()) {
            return HandleResponse.error("Mẫu sản phẩm không tồn tại");
        }
        var canNotDelete = orderItemRepository.existsByProductOptionId(deleteProductOptionCommand.id());
        if (canNotDelete) {
            System.out.println("soft delete");
            productOptionRepository.delete(productOption.get());

        }else {
            System.out.println("hard delete");
            productOptionRepository.hardDeleteById(deleteProductOptionCommand.id());
        }
        return HandleResponse.ok();
    }
}
