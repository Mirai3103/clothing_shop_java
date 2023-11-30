package com.shop.clothing.report.query.getTopSoldProductReport;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.report.dto.ProductReportDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetTopSoldProductReportQuery implements IRequest<List<ProductReportDto>> {
    Long startDateTimestamp;
    Long endDateTimeStamp;

    public Date getStartDate() {
        return startDateTimestamp == null ?null: new Date(startDateTimestamp);
    }

    public Date getEndDate() {
        return endDateTimeStamp == null ? null : new Date(endDateTimeStamp);
    }

}
