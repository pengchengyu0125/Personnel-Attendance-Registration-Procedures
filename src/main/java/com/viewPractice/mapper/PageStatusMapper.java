package com.viewPractice.mapper;

import com.viewPractice.dto.InfoDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface PageStatusMapper {
    //查询数据总数
    @Select("select count(1) from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_name regexp #{visitName} and h.visit_phone regexp #{visitPhone} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status}")
    Integer count1(@Param(value = "visitName") String visitName, @Param(value = "visitPhone") String visitPhone, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    @Select("select count(1) from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_phone regexp #{visitPhone} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status}")
    Integer count2(@Param(value = "visitPhone") String visitPhone, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    @Select("select count(1) from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_name regexp #{visitName} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status}")
    Integer count3(@Param(value = "visitName") String visitPhone, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    @Select("select count(1) from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status}")
    Integer count4(@Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    //根据分页规则查询内容
    //根据查询条件查询内容，且名字不为空
    @Select("select h.*,i.status from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone  where h.visit_name regexp #{visitName} and h.visit_phone regexp #{visitPhone} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status} order by i.status, h.create_time desc limit #{offset},#{size}")
    List<InfoDTO> selectInfo(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size, @Param(value = "visitName") String visitName, @Param(value = "visitPhone")  String visitPhone, @Param(value = "startTime")  String startTime, @Param(value = "endTime")  String endTime, @Param(value = "status") String status);

    //根据查询条件查询内容，名字为空
    @Select("select h.*,i.status from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_phone regexp #{visitPhone} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status} order by i.status, h.create_time desc limit #{offset},#{size}")
    List<InfoDTO> selectInfo1(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size, @Param(value = "visitPhone") String visitPhone, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    //根据查询条件查询内容，手机号为空
    @Select("select h.*,i.status from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_name regexp #{visitName} and h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status} order by i.status, h.create_time desc limit #{offset},#{size}")
    List<InfoDTO> selectInfo2(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size, @Param(value = "visitName") String visitPhone, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

    @Select("select h.*,i.status from t_mg_visit_infodept_history AS h LEFT JOIN t_mg_visit_infodept_info AS i ON h.visit_phone = i.visit_phone where h.visit_time >= #{startTime} and h.visit_time <= #{endTime} and i.status = #{status} order by i.status, h.create_time desc limit #{offset},#{size}")
    List<InfoDTO> selectInfo3(@Param(value = "offset") Integer offset, @Param(value = "size") Integer size, @Param(value = "startTime") String startTime, @Param(value = "endTime") String endTime, @Param(value = "status") String status);

}
