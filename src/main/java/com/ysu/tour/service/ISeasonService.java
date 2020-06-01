package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Season;

import java.util.List;

public interface ISeasonService {


    ServerResponse selectAll();
    ServerResponse find(Integer id);

//    以下是管理员
    List<Season> sysselectAll();
    int sysupdateStatus( Integer status,  Integer id);
    int  sysinsert(Season season);
}
