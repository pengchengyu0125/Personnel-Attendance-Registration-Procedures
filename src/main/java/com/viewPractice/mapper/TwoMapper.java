package com.viewPractice.mapper;

import com.viewPractice.dto.TwoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface TwoMapper {
    //查询页面二的进度信息
    @Select("select * from two order by id asc")
    List<TwoDTO> selectInfo();
}
