package com.shop.clothing.product.query.getAllProducts;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.entity.Product;
import com.shop.clothing.product.repository.ProductRepository;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@AllArgsConstructor
@Service
public class GetAllProductsQueryHandler implements IRequestHandler<GetAllProductsQuery, Paginated<ProductBriefDto>> {
    private final ProductRepository productRepository;
    private final EntityManager entityManager;
    private final ModelMapper modelMapper;

    @Override
    public HandleResponse<Paginated<ProductBriefDto>> handle(GetAllProductsQuery getAllProductsQuery) {
        var cb = entityManager.getCriteriaBuilder();
        var cq = cb.createQuery(Product.class);
        var root = cq.from(Product.class);
        var predicates = cb.conjunction();
        if (getAllProductsQuery.getCategoryId() != 0) {
            predicates = cb.and(predicates, cb.equal(root.get("category").get("id"), getAllProductsQuery.getCategoryId()));
        }
        if (getAllProductsQuery.getForGender() != null) {
            predicates = cb.and(predicates, cb.equal(root.get("forGender"), getAllProductsQuery.getForGender()));
        }
        if (getAllProductsQuery.getMinPrice() != 0) {
            predicates = cb.and(predicates, cb.greaterThanOrEqualTo(root.get("price"), getAllProductsQuery.getMinPrice()));
        }
        if (getAllProductsQuery.getMaxPrice() != Integer.MAX_VALUE) {
            predicates = cb.and(predicates, cb.lessThanOrEqualTo(root.get("price"), getAllProductsQuery.getMaxPrice()));
        }
        if (!getAllProductsQuery.getKeyword().isBlank()) {
            predicates = cb.and(predicates, cb.like(root.get("name"), "%" + getAllProductsQuery.getKeyword() + "%"));
        }

        if (!getAllProductsQuery.isIncludeDeleted()) {
            predicates = cb.and(predicates, cb.isNull(root.get("deletedDate")));
        }
        String sortField = getAllProductsQuery.getSortField();
        if (sortField.isBlank()) {
            sortField = "createdDate";
        }
        if (getAllProductsQuery.getSortDir().equals("desc")) {
            cq.orderBy(cb.desc(root.get(sortField)));
        } else {
            cq.orderBy(cb.asc(root.get(sortField)));
        }
        cq.where(predicates);
        var query = entityManager.createQuery(cq);
        query.setFirstResult((getAllProductsQuery.getPage() - 1) * getAllProductsQuery.getSize());
        query.setMaxResults(getAllProductsQuery.getSize());
        Collection<Product> result = query.getResultList();
        List<ProductBriefDto> productBriefDtos = result.stream().map(product ->{
            return modelMapper.map(product, ProductBriefDto.class);
        }).toList();

        var count = productRepository.count();
        var paginated = Paginated.<ProductBriefDto>builder().size(getAllProductsQuery.getSize()).page(getAllProductsQuery.getPage()).data(productBriefDtos).totalElements(count).totalPages((int) Math.ceil((double) count / getAllProductsQuery.getSize())).build();


        return HandleResponse.ok(paginated);
    }
}
