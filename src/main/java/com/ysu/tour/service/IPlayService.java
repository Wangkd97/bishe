package com.ysu.tour.service;


import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Play;

import java.util.List;

public interface IPlayService {

    ServerResponse selectAll();
    ServerResponse modifyAll();

//    以下是管理员
    List<Play> sysselectAll();
    int sysupdateStatus( Integer status,  Integer id);
    int sysinsert(Play play);
}
