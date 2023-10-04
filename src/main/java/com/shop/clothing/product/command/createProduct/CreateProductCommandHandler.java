package com.shop.clothing.product.command.createProduct;

import com.shop.clothing.category.CategoryRepository;
import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.util.SlugUtil;
import com.shop.clothing.product.entity.Product;
import com.shop.clothing.product.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class CreateProductCommandHandler implements IRequestHandler<CreateProductCommand, Integer> {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final SlugUtil slugUtil;

    @Override
    @Transactional
    public HandleResponse<Integer> handle(CreateProductCommand createProductCommand) {
        var existCategory = categoryRepository.findById(createProductCommand.getCategoryId());
        if (existCategory.isEmpty()) {
            return HandleResponse.error("Danh mục sản phẩm không tồn tại");
        }
        var product = Product.builder()
                .category(existCategory.get())
                .name(createProductCommand.getName())
                .description(createProductCommand.getDescription())
                .displayImage(createProductCommand.getDisplayImage())
                .discount(createProductCommand.getDiscount())
                .forGender(createProductCommand.getForGender())
                .price(createProductCommand.getPrice())
                .slug(slugUtil.slugify(createProductCommand.getName()))
                .build();
        productRepository.save(product);
        return HandleResponse.ok(product.getProductId());
    }
}
