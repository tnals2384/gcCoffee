package org.cafe.gccoffee.model.dto.product;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProductResponsePage {
    private List<ProductResponse> productResponseList = new ArrayList<>();
    private int page;
    private int totalPages;
    private int totalElements;
    private Boolean isFirst;
    private Boolean isLast;

    @Builder
    private ProductResponsePage(List<ProductResponse> productResponseList, int page, int totalPages, int totalElements, Boolean isFirst, Boolean isLast) {
        this.productResponseList = productResponseList;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    public static ProductResponsePage productResponsePageOf(List<ProductResponse> productResponseList,
                                                            int page,
                                                            int size,
                                                            int totalCount) {
        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean isFirst = (page == 0);
        boolean isLast = (page >= totalPages - 1);

        return ProductResponsePage.builder()
                .productResponseList(productResponseList)
                .page(page)
                .totalPages(totalPages)
                .totalElements(totalCount)
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }
}
