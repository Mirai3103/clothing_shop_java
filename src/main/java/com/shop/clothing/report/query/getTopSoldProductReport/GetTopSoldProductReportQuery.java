package com.shop.clothing.report.query.getTopSoldProductReport;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.report.dto.ProductReportDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class GetTopSoldProductReportQuery implements IRequest<List<ProductReportDto>> {
    Long startDateTimestamp;
    Long endDateTimeStamp;

    public LocalDate getStartDate() {
        return startDateTimestamp == null ? LocalDate.of(2010, 1, 1) : LocalDate.ofEpochDay(startDateTimestamp);
    }

    public LocalDate getEndDate() {
        return endDateTimeStamp == null ? LocalDate.now().plusDays(1) : LocalDate.ofEpochDay(endDateTimeStamp);
    }

}
