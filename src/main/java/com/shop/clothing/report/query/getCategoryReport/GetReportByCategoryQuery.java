package com.shop.clothing.report.query.getReportByCategory;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.report.dto.CategoryReportDto;

import java.sql.Date;
import java.util.List;

public class GetReportByCategoryQuery implements IRequest<List<CategoryReportDto>> {
    Long startDateTimestamp;
    Long endDateTimeStamp;

    public Date getStartDate() {
        return startDateTimestamp == null ?null: new Date(startDateTimestamp);
    }

    public Date getEndDate() {
        return endDateTimeStamp == null ? null : new Date(endDateTimeStamp);
    }
}
