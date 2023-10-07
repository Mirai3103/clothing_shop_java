package com.shop.clothing.product.command.createProductOption;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.product.entity.ProductOption;
import com.shop.clothing.product.repository.ColorRepository;
import com.shop.clothing.product.repository.ProductOptionRepository;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@AllArgsConstructor
@Service
public class CreateProductOptionCommandHandler implements IRequestHandler<CreateProductOptionCommand, Void> {
    private final ProductOptionRepository productOptionRepository;
    private final ProductRepository productRepository;
    private final ColorRepository colorRepository;

    @Override
    @Transactional
    public HandleResponse<Void> handle(CreateProductOptionCommand createProductOptionCommand) {
        createProductOptionCommand.setSize(createProductOptionCommand.getSize().toUpperCase());
        var product = productRepository.findById(createProductOptionCommand.getProductId());
        if (product.isEmpty()) {
            return HandleResponse.error("Sản phẩm không tồn tại");
        }
        var color = colorRepository.findById(createProductOptionCommand.getColorId());
        if (color.isEmpty()) {
            return HandleResponse.error("Màu không tồn tại");
        }
        var productOption = productOptionRepository.findFirstByProductIdAndColorIdAndSize(createProductOptionCommand.getProductId(), createProductOptionCommand.getColorId(), createProductOptionCommand.getSize());
        if (productOption.isPresent()) {
            if (productOption.get().getDeletedDate() != null) {
                productOption.get().setDeletedDate(null);
                productOptionRepository.save(productOption.get());
                return HandleResponse.ok();
            }
            return HandleResponse.error("Mẫu sản phẩm đã tồn tại");
        }
        var newProductOption = ProductOption.builder().product(product.get()).color(color.get()).size(createProductOptionCommand.getSize()).stock(createProductOptionCommand.getStock()).build();
        productOptionRepository.save(newProductOption);
        return HandleResponse.ok();
    }

}
