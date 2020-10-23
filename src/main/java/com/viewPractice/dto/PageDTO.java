package com.viewPractice.dto;

import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/*
分页信息DTO
 */

@Data
public class PageDTO {
    private List<InfoDTO> infoDTOS;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer page;
    private List<Integer> pages=new ArrayList<>();
    private Integer totalPage;
    private Integer size;
    private String visitName;
    private String visitPhone;
    private String startTime;
    private String endTime;
    private String status;

    //设置分页展示情况
    public void setPagination(Integer totalPage, Integer page) {
        this.totalPage=totalPage;//总页数
        this.page=page;//当前页码
        pages.add(page);
        //在当前页码前后增加三页
        for (int i = 1; i <= 3; i++){
            if (page - i > 0)
                pages.add(0,page - i);
            if (page + i <= totalPage)
                pages.add(page + i);
        }
        //是否展示上一页
        if (page == 1){
            showPrevious = false;
        }
        else {
            showPrevious = true;
        }
        //是否展示下一页
        if (page == totalPage){
            showNext = false;
        }
        else {
            showNext = true;
        }
        //是否展示第一页
        if (pages.contains(1)){
            showFirstPage = false;
        }
        else {
            showFirstPage = true;
        }
        //是否展示最后一页
        if (pages.contains(totalPage)){
            showEndPage = false;
        }
        else {
            showEndPage = true;
        }
    }
}
