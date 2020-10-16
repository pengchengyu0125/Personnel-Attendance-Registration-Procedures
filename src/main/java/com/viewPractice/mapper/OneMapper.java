package com.viewPractice.mapper;

import com.viewPractice.dto.OneDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface OneMapper {
    //查询页面一的进度信息
    @Select("select process from one order by id asc")
    List<OneDTO> selectProcess();
}
