package com.shop.clothing.report.query.getTopSoldProductReport;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.product.dto.ProductBriefDto;
import com.shop.clothing.product.repository.ProductRepository;
import com.shop.clothing.report.dto.ProductReportDto;
import jakarta.persistence.Tuple;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@AllArgsConstructor
@Service
public class GetTopSoldProductReportQueryHandler implements IRequestHandler<GetTopSoldProductReportQuery, List<ProductReportDto>> {
    private final ProductRepository productRepository;

    @Override
    @Transactional(readOnly = true)
    public HandleResponse<List<ProductReportDto>> handle(GetTopSoldProductReportQuery getTopSoldProductReportQuery) throws Exception {
        List<Tuple> productReportDtoTupleList = productRepository.getSoldReport(getTopSoldProductReportQuery.getStartDate(), getTopSoldProductReportQuery.getEndDate());
        List<ProductReportDto> productReportDtoList = productReportDtoTupleList.stream().map(tuple -> {
            var productReportDto = new ProductReportDto();
            var productBriefDto = new ProductBriefDto();
            var productImage = tuple.get("displayImage", String.class);
            var productName = tuple.get("name", String.class);
            int productID = tuple.get("productId", Integer.class);
            productReportDto.setTotalSold(Math.toIntExact(tuple.get("total_sold", Long.class)));
            productReportDto.setTotalRevenue(Math.toIntExact(tuple.get("total_revenue", Long.class)));
            productBriefDto.setDisplayImage(productImage);
            productBriefDto.setName(productName);
            productBriefDto.setProductId(productID);
            productReportDto.setProduct(productBriefDto);
            return productReportDto;
        }).toList();
        return HandleResponse.ok(productReportDtoList);

    }
}
