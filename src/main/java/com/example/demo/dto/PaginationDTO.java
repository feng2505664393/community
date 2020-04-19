package com.example.demo.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * 页面的对象
 */
@Data
public class PaginationDTO {
    private List<QuestionDTO> questionDTOList;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages = new ArrayList<>();
    private Integer totalPage;

    public void setPagination(Integer totalPage, Integer page, Integer size) {
        showPrevious = (page == 1) ? false : true;   //是否展示前一页按钮
        showNext = (page == totalPage) ? false : true;   //是否展示后一页按钮
        showFirstPage = (!pages.contains(1)) ? true : false;  //是否展示第一页跳转按钮
        showEndPage = (!pages.contains(totalPage)) ? true : false;  //是否展示最后一页跳转按钮
        this.page = page;
        this.totalPage=totalPage;
        pages.add(page);
        for (int i = 1; i <= 3; i++) {
            if (page - i > 0) pages.add(0, page - i);
            if (page + i <= totalPage) pages.add(page + i);
        }
    }
}
