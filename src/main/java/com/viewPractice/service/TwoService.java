package com.viewPractice.service;

import com.viewPractice.dto.TwoDTO;
import com.viewPractice.mapper.TwoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
页面二Service
 */

@Service
public class TwoService {
    @Autowired
    private TwoMapper twoMapper;

    //获取数据库中查询的页面二详细信息
    public List<TwoDTO> list(){
        List<TwoDTO> twoDTOS=twoMapper.selectInfo();
        return twoDTOS;
    }
}