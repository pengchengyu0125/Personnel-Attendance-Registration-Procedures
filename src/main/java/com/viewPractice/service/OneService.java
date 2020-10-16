package com.viewPractice.service;

import com.viewPractice.dto.OneDTO;
import com.viewPractice.mapper.OneMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/*
页面一Service
 */

@Service
public class OneService {
    @Autowired
    private OneMapper oneMapper;

    public List<OneDTO> list(){
        //获取数据库中查询的页面一详细信息
        List<OneDTO> oneDTOS=oneMapper.selectProcess();
        return oneDTOS;
    }
}
