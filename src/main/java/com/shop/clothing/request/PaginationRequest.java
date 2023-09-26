package com.shop.clothing.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
@Getter
public class PaginationRequest {
    @Min(value = 1,message = "Page must be greater than 0")
    private int page = 1;
    private int size = 10;
    private String sortField="";
    private String sortDir="asc";
    private String keyword = "";

    public Sort.Direction getSortDirection() {
        return sortDir.equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
    }

    public Pageable getPageable() {
        return org.springframework.data.domain.PageRequest.of(page - 1, size, getSortDirection(), sortField);
    }
}
