package com.ysu.tour.service;

import com.ysu.tour.pojo.Active;
import com.ysu.tour.pojo.Category;

import javax.sql.rowset.CachedRowSet;
import java.util.List;

public interface IActiveService {

    int insert(Active a);
    List<Category> selectTopFive();


    int sysUpdateStatus(Active a);
    List<Active>sysSelectTopTen();

    int delete();



}
