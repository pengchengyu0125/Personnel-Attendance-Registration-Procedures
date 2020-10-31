package com.viewPractice.service;

import com.viewPractice.dto.InfoDTO;
import com.viewPractice.dto.PageDTO;
import com.viewPractice.mapper.PageMapper;
import com.viewPractice.mapper.PageStatusMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/*
分页页面Service
 */

@Service
public class PageService {
    @Autowired
    private PageMapper pageMapper;

    @Autowired
    private PageStatusMapper pageStatusMapper;

    //获取页面DTO信息
    public PageDTO list(int page, int size, String visitName, String visitPhone, String startTime, String endTime, String status){
        status = "2";
        PageDTO pageDTO = new PageDTO();
        pageDTO.setSize(size);
        pageDTO.setVisitName(visitName);
        pageDTO.setVisitPhone(visitPhone);
        pageDTO.setStartTime(startTime);
        pageDTO.setEndTime(endTime);
        pageDTO.setStatus(status);
        //总页面数
        Integer totalPage;
        //总数据数
        Integer totalCount = 1;
        if (status.equals("2")) {
            //查询值为空时查询数据总数
            if (((("null").equals(visitName) && ("null").equals(visitPhone)) || (visitName == null && visitPhone == null) || (("").equals(visitName) && ("").equals(visitPhone))) && (startTime == null || ("").equals(startTime)))
                totalCount = pageMapper.count();
                //查询姓名不为空且手机号不为空时查询数据总数
            else if (!("").equals(visitName) && !("").equals(visitPhone)) {
                totalCount = pageMapper.count1(visitName, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59");
                if (totalCount < 1)
                    totalCount = 1;
            }
            //查询姓名为空且手机号不为空时查询数据总数
            else if (("").equals(visitName) && !("").equals(visitPhone)) {
                totalCount = pageMapper.count2(visitPhone, startTime + " 00:00:00", endTime + " 23:59:59");
                if (totalCount < 1)
                    totalCount = 1;
            }
            //查询姓名不为空且手机号为空时查询数据总数
            else if (("").equals(visitPhone) && !("").equals(visitName)) {
                totalCount = pageMapper.count3(visitName, startTime + " 00:00:00", endTime + " 23:59:59");
                if (totalCount < 1)
                    totalCount = 1;
            }
            //只输入查询时间时查询数据总数
            else if (("").equals(visitPhone) && ("").equals(visitName) && !("").equals(startTime)) {
                totalCount = pageMapper.count4(startTime + " 00:00:00", endTime + " 23:59:59");
                if (totalCount < 1)
                    totalCount = 1;
            }
        }
        else {
            //查询姓名不为空且手机号不为空时查询数据总数
            if (!("").equals(visitName) && !("").equals(visitPhone)) {
                totalCount = pageStatusMapper.count1(visitName, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59", status);
                if (totalCount < 1)
                    totalCount = 1;
            }
            //查询姓名为空且手机号不为空时查询数据总数
            else if (("").equals(visitName) && !("").equals(visitPhone)) {
                totalCount = pageStatusMapper.count2(visitPhone, startTime + " 00:00:00", endTime + " 23:59:59", status);
                if (totalCount < 1)
                    totalCount = 1;
            }
            //查询姓名不为空且手机号为空时查询数据总数
            else if (("").equals(visitPhone) && !("").equals(visitName)) {
                totalCount = pageStatusMapper.count3(visitName, startTime + " 00:00:00", endTime + " 23:59:59", status);
                if (totalCount < 1)
                    totalCount = 1;
            }
            //只输入查询时间时查询数据总数
            else if (("").equals(visitPhone) && ("").equals(visitName) && !("").equals(startTime)) {
                totalCount = pageStatusMapper.count4(startTime + " 00:00:00", endTime + " 23:59:59", status);
                if (totalCount < 1)
                    totalCount = 1;
            }
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
        if (status.equals("2")) {
            //查询值为空时查询所有信息记录
            if (((("null").equals(visitName) && ("null").equals(visitPhone)) || (visitName == null && visitPhone == null) || (("").equals(visitName) && ("").equals(visitPhone))) && (startTime == null || ("").equals(startTime))) {
                pageDTO.setInfoDTOS(pageMapper.selectInfo0(offset, size));
            } else if (visitName.isEmpty() == true && visitPhone.isEmpty() == true && startTime.isEmpty() == false)
                pageDTO.setInfoDTOS(pageMapper.selectInfo3(offset, size, startTime + " 00:00:00", endTime + " 23:59:59"));
            else if (visitName.isEmpty() == false && visitPhone.isEmpty() == false)
                pageDTO.setInfoDTOS(pageMapper.selectInfo(offset, size, visitName, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59"));
            else if (visitName.isEmpty() == true && visitPhone.isEmpty() == false)
                pageDTO.setInfoDTOS(pageMapper.selectInfo1(offset, size, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59"));
            else if (visitName.isEmpty() == false && visitPhone.isEmpty() == true)
                pageDTO.setInfoDTOS(pageMapper.selectInfo2(offset, size, visitName, startTime + " 00:00:00", endTime + " 23:59:59"));
        }
        else {
            //查询值为空时查询所有信息记录
            if (visitName.isEmpty() == true && visitPhone.isEmpty() == true && startTime.isEmpty() == false)
                pageDTO.setInfoDTOS(pageStatusMapper.selectInfo3(offset, size, startTime + " 00:00:00", endTime + " 23:59:59", status));
            else if (visitName.isEmpty() == false && visitPhone.isEmpty() == false)
                pageDTO.setInfoDTOS(pageStatusMapper.selectInfo(offset, size, visitName, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59", status));
            else if (visitName.isEmpty() == true && visitPhone.isEmpty() == false)
                pageDTO.setInfoDTOS(pageStatusMapper.selectInfo1(offset, size, visitPhone, startTime + " 00:00:00", endTime + " 23:59:59", status));
            else if (visitName.isEmpty() == false && visitPhone.isEmpty() == true)
                pageDTO.setInfoDTOS(pageStatusMapper.selectInfo2(offset, size, visitName, startTime + " 00:00:00", endTime + " 23:59:59", status));
        }
        return pageDTO;

    }
    //更新审核状态
    public void updateStatus(String[] phoneList){
        for (int i = 0; i < phoneList.length; i++) {
            pageMapper.update(phoneList[i]);
        }
    }

    public PageDTO list1(Integer page, Integer size, String visitName, String visitPhone, String startTime, String endTime, String status) {
        PageDTO pageDTO = new PageDTO();
        pageDTO.setSize(size);
        pageDTO.setVisitName(visitName);
        pageDTO.setVisitPhone(visitPhone);
        pageDTO.setStartTime(startTime);
        pageDTO.setEndTime(endTime);
        pageDTO.setStatus(status);
        //总页面数
        Integer totalPage;
        //总数据数
        Integer totalCount = 1;
        totalPage = 1;
        //判断页面总数
        page = 1;
        pageDTO.setPagination(totalPage, page);
        Integer offset = size * (page - 1);//当前起始数据索引
        InfoDTO infoDTO = new InfoDTO();
        List<InfoDTO> infoDTOS = new ArrayList<>();
        infoDTOS.add(infoDTO);
        pageDTO.setInfoDTOS(infoDTOS);
        return pageDTO;
    }
}
