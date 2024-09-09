package org.cafe.gccoffee.util;

import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

public class PageUtils<T> {
    private List<T> list = new ArrayList<>();
    private int page;
    private int totalPages;
    private int totalElements;
    private Boolean isFirst;
    private Boolean isLast;

    @Builder
    private PageUtils(List<T> list, int page, int totalPages, int totalElements, Boolean isFirst, Boolean isLast) {
        this.list = list;
        this.page = page;
        this.totalPages = totalPages;
        this.totalElements = totalElements;
        this.isFirst = isFirst;
        this.isLast = isLast;
    }

    public static <T> PageUtils<T> pageUtilsOf(List<T> list,
                                                            int page,
                                                            int size,
                                                            int totalCount) {
        int totalPages = (int) Math.ceil((double) totalCount / size);
        boolean isFirst = (page == 0);
        boolean isLast = (page >= totalPages - 1);

        return PageUtils.<T>builder()
                .list(list)
                .page(page)
                .totalPages(totalPages)
                .totalElements(totalCount)
                .isFirst(isFirst)
                .isLast(isLast)
                .build();
    }

    public static void checkPagingRequest(int page, int size) {
        if(page < 0 || size <= 0)
            throw new RuntimeException("잘못된 페이징 요청입니다.");
    }
}