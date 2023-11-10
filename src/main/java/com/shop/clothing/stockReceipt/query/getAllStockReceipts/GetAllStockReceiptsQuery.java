package com.shop.clothing.stockReceipt.query.getAllStockReceipts;

import com.shop.clothing.common.Cqrs.IRequest;
import com.shop.clothing.common.PaginationRequest;
import com.shop.clothing.common.dto.Paginated;
import com.shop.clothing.stockReceipt.dto.StockReceiptBriefDto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Setter
public class GetAllStockReceiptsQuery extends PaginationRequest implements IRequest<Paginated<StockReceiptBriefDto>> {
    private Long startDate;
    private Long endDate;
    @Getter

    private Integer supplierId;
    @Getter

    private int totalFrom;
    @Getter

    private int totalTo;

    public Date getStartDateObject() {
        return new Date(startDate);
    }

    public Date getEndDateObject() {
        return new Date(endDate);
    }
}
