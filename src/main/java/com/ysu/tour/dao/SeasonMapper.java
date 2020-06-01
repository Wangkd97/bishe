package com.ysu.tour.dao;

import com.ysu.tour.pojo.Season;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SeasonMapper {

    int deleteByPrimaryKey(Integer seasonId);

    int insert(Season record);

    Season selectByPrimaryKey(Integer seasonId);

    List<Season> selectAll();
    int updateByPrimaryKey(Season record);
    Season selectById(Integer id);


//    以下是管理员

    List<Season> sysselectAll();
    int sysupdateStatus(@Param("status") Integer status, @Param("id") Integer id);
    int sysinsert(Season season);

}