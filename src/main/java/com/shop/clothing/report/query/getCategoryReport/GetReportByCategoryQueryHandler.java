package com.shop.clothing.report.query.getReportByCategory;

import com.shop.clothing.common.Cqrs.HandleResponse;
import com.shop.clothing.common.Cqrs.IRequestHandler;
import com.shop.clothing.report.dto.CategoryReportDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetReportByCategoryQueryHandler implements IRequestHandler<com.shop.clothing.report.query.getReportByCategory.GetReportByCategoryQuery,List<CategoryReportDto>>{


    @Override
    public HandleResponse<List<CategoryReportDto>> handle(com.shop.clothing.report.query.getReportByCategory.GetReportByCategoryQuery getReportByCategoryQuery) throws Exception {
        return null;
    }
}
