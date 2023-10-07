package com.shop.clothing.product.command.deleteProduct;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class DeleteProductCommandHandler implements IRequestHandler<DeleteProductCommand, Void> {
    private final ProductRepository productRepository;

    @Override
    public HandleResponse<Void> handle(DeleteProductCommand deleteProductCommand) {
        var product = productRepository.findById(deleteProductCommand.id());
        if (product.isEmpty()) {
            return HandleResponse.error("Product not found");
        }
        productRepository.delete(product.get());
        return HandleResponse.ok();
    }
}
