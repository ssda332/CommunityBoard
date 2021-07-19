package com.yj.community.domain.board.pagination;

import lombok.Data;

@Data
public class PageInfo {

    public PageInfo(int currentPage, int listCount, int pageLimit, int maxPage, int startPage, int endPage,
                    int boardLimit) {
        super();
        this.currentPage = currentPage;
        this.listCount = listCount;
        this.pageLimit = pageLimit;
        this.maxPage = maxPage;
        this.startPage = startPage;
        this.endPage = endPage;
        this.boardLimit = boardLimit;
    }

    private int currentPage;
    private int listCount;
    private int pageLimit;
    private int maxPage;
    private int startPage;
    private int endPage;
    private int boardLimit;

}
