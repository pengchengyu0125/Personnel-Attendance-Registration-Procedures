package com.viewPractice.service;

import com.viewPractice.dto.PageDTO;
import com.viewPractice.mapper.PageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
分页页面Service
 */

@Service
public class PageService {
    @Autowired
    private PageMapper pageMapper;

    //获取页面DTO信息
    public PageDTO list(int page, int size, String visitName, String visitPhone, String startTime, String endTime){
        PageDTO pageDTO = new PageDTO();
        pageDTO.setSize(size);
        pageDTO.setVisitName(visitName);
        pageDTO.setVisitPhone(visitPhone);
        pageDTO.setStartTime(startTime);
        pageDTO.setEndTime(endTime);
        Integer totalPage;
        Integer totalCount = 1;
        if ((("null").equals(visitName) && ("null").equals(visitPhone)) || (visitName == null && visitPhone == null))
            totalCount = pageMapper.count();//查询数据总数;
        else if (!("").equals(visitName) && !("").equals(visitPhone)) {
            totalCount = pageMapper.count1(visitName, visitPhone, startTime+" 00:00:00", endTime+" 24:00:00");//查询数据总数
            if (totalCount <1)
                totalCount = 1;
        }
        else if (("").equals(visitName) && !("").equals(visitPhone)) {
            totalCount = pageMapper.count2(visitPhone, startTime+" 00:00:00", endTime+" 24:00:00");//查询数据总数
            if (totalCount <1)
                totalCount = 1;
        }
        else if (("").equals(visitPhone) && !("").equals(visitName)) {
            totalCount = pageMapper.count3(visitName, startTime+" 00:00:00", endTime+" 24:00:00");//查询数据总数
            if (totalCount <1)
                totalCount = 1;
        }
        else if (("").equals(visitPhone) && ("").equals(visitName) && !("").equals(startTime)) {
            totalCount = pageMapper.count4(startTime+" 00:00:00", endTime+" 24:00:00");//查询数据总数
            if (totalCount <1)
                totalCount = 1;
        }
        //判断页面总数
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }
        else {
            totalPage = totalCount / size + 1;
        }
        //判断当前页码
        if (page < 1){
            page = 1;
        }
        if (page > totalPage) {
            page = totalPage;
        }
        pageDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);//当前起始数据索引
        if ((("null").equals(visitName) && ("null").equals(visitPhone)) || (visitName == null && visitPhone == null)){
            pageDTO.setInfoDTOS(pageMapper.selectInfo0(offset, size));
        }
        else if (visitName.isEmpty() == true && visitPhone.isEmpty() == true && startTime.isEmpty() == false)
            pageDTO.setInfoDTOS(pageMapper.selectInfo3(offset, size, startTime+" 00:00:00", endTime+" 24:00:00"));
        else if (visitName.isEmpty() == false && visitPhone.isEmpty() == false)
            pageDTO.setInfoDTOS(pageMapper.selectInfo(offset, size, visitName, visitPhone, startTime+" 00:00:00", endTime+" 24:00:00"));
        else if (visitName.isEmpty() == true && visitPhone.isEmpty() == false)
            pageDTO.setInfoDTOS(pageMapper.selectInfo1(offset, size, visitPhone, startTime+" 00:00:00", endTime+" 24:00:00"));
        else if (visitName.isEmpty() == false && visitPhone.isEmpty() == true)
            pageDTO.setInfoDTOS(pageMapper.selectInfo2(offset, size, visitName, startTime+" 00:00:00", endTime+" 24:00:00"));
        return pageDTO;
    }
    //更新审核状态
    public void updateStatus(String[] phoneList){
        for (int i = 0; i < phoneList.length; i++) {
            pageMapper.update(phoneList[i]);
        }
    }
}
