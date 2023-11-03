package com.shop.clothing.product.query.advanceSearchProduct;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.entity.Product;
import com.shop.clothing.product.query.getAllProducts.GetAllProductsQuery;
import com.shop.clothing.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

@AllArgsConstructor
@Service
public class AdvanceSearchAllProductsQueryHandler implements IRequestHandler<AdvanceSearchAllProductsQuery, Paginated<ProductBriefDto>> {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    @Cacheable(value = "searchProduct", key = "#query.toString()")
    public HandleResponse<Paginated<ProductBriefDto>> handle(AdvanceSearchAllProductsQuery query) throws Exception {
        query.setSizes(Arrays.stream(query.getSizes()).map(String::toUpperCase).toArray(String[]::new));
        var productPage = productRepository.searchAllProducts(query.getKeyword(), query.getCategoryIds(), query.getForGenders(), query.getMinPrice(), query.getMaxPrice(), query.getColorIds(), query.getSizes(), query.getPageable("createdDate"));
        Paginated<ProductBriefDto> paginated = Paginated.<ProductBriefDto>builder()
                .totalElements(productPage.getTotalElements())
                .data(productPage.getContent().stream().map(product -> modelMapper.map(product, ProductBriefDto.class)).toList())
                .totalPages(productPage.getTotalPages())
                .page(productPage.getNumber() + 1)
                .pageSize(productPage.getSize())
                .hasNext(productPage.hasNext())
                .hasPrevious(productPage.hasPrevious())
                .build();
        return HandleResponse.ok(paginated);
    }
}
