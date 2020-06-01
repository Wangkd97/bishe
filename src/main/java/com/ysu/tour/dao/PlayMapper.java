package com.ysu.tour.dao;

import com.ysu.tour.pojo.Play;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface PlayMapper {

    int deleteByPrimaryKey(Integer playId);
    int insert(Play record);
    Play selectByPrimaryKey(Integer playId);
    List<Play> selectAll();

    int updateByPrimaryKey(Play record);
    List<Play> selectById(Integer id);


//    以下是管理员

    List<Play> sysselectAll();
    int sysupdateStatus(@Param("status") Integer status,@Param("id") Integer id);
    int sysinsert(Play play);


}