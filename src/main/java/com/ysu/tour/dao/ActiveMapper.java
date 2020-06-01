package com.ysu.tour.dao;

import com.ysu.tour.pojo.Active;
import com.ysu.tour.pojo.Category;

import java.util.List;

public interface ActiveMapper {

    int insert(Active a);
    List<Category> selectTopFive();


    int sysUpdateStatus(Active a);
    List<Active>sysSelectTopTen();

    int delete();
}
