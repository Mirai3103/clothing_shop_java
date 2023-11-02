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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class AdvanceSearchAllProductsQueryHandler implements IRequestHandler<AdvanceSearchAllProductsQuery, Paginated<ProductBriefDto>> {
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public HandleResponse<Paginated<ProductBriefDto>> handle(AdvanceSearchAllProductsQuery query) throws Exception {
//        var cb = entityManager.getCriteriaBuilder();
//        var cq = cb.createQuery(Product.class);
//        var root = cq.from(Product.class);
//        var predicates = cb.conjunction();
//        if (query.getCategoryIds() != null && query.getCategoryIds().length > 0) {
//            predicates = cb.and(predicates, root.get("category").get("categoryId").in(List.of(query.getCategoryIds())));
//        }
//        if (query.getForGenders() != null && query.getForGenders().length > 0) {
//            predicates = cb.and(predicates, root.get("forGender").in(List.of(query.getForGenders())));
//        }
//        if (query.getMaxPrice() != Integer.MAX_VALUE) {
//            predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("price"), query.getMaxPrice()));
//        }
//        if (query.getMinPrice() != 0) {
//            predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("price"), query.getMinPrice()));
//        }
//        if (!query.getKeyword().isBlank()) {
//            predicates = cb.and(predicates, cb.like(root.get("name"), "%" + query.getKeyword() + "%"));
//        }
//
//
//        String sortField = query.getSortField();
//        if (sortField.isBlank()) {
//            sortField = "createdDate";
//        }
//        if (query.getSortDir().equals("desc")) {
//            cq.orderBy(cb.desc(root.get(sortField)));
//        } else {
//            cq.orderBy(cb.asc(root.get(sortField)));
//        }
//        cq.where(predicates);
//        var managerQuery = entityManager.createQuery(cq);
//        var count = managerQuery.getResultList().size();
//        managerQuery.setFirstResult((query.getPage() - 1) * query.getPageSize());
//        managerQuery.setMaxResults(query.getPageSize());
//        Collection<Product> result = managerQuery.getResultList();
//        List<ProductBriefDto> productBriefDtos = result.stream().map(product -> modelMapper.map(product, ProductBriefDto.class)
//        ).toList();
//        var paginated = Paginated.<ProductBriefDto>builder().pageSize(query.getPageSize()).page(query.getPage()).data(productBriefDtos).totalElements(count).totalPages((int) Math.ceil((double) count / query.getPageSize())).build();
//        paginated.setHasNext(paginated.getPage() < paginated.getTotalPages());
//        paginated.setHasPrevious(paginated.getPage() > 1);
        var productPage = productRepository.searchAllProducts( query.getKeyword(), query.getCategoryIds(), query.getForGenders(), query.getMinPrice(), query.getMaxPrice(),query.getColorIds(),query.getSizes(),query.getPageable("createdDate"));
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
