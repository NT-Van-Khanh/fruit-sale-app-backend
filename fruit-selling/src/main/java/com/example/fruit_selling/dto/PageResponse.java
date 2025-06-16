package com.example.fruit_selling.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class PageResponse<T> {
    private List<T> data;
    private long totalItems;
    private int currentPage;
    private int totalPages;

    public PageResponse(List<T> data, Page<?> page) {
        this.data = data;
        this.currentPage = page.getNumber();
        this.totalPages = page.getTotalPages();
        this.totalItems = page.getTotalElements();
    }
}
