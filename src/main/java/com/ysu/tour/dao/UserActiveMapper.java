package com.ysu.tour.dao;


import com.ysu.tour.pojo.UserActive;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserActiveMapper {
    UserActive selectByuId(Integer uId);
    int updateClick(Integer uId);
    int updateComment(Integer uId);
    int updateLook(Integer uId);
    int insert(Integer uId);
    int updateActive(@Param("userActiveNum") Integer userActiveNum,@Param("uId") Integer uId);
    List<UserActive> selectAll();
}
