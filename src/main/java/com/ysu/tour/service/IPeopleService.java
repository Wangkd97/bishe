package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.People;

import java.util.List;

public interface IPeopleService {

   ServerResponse selectAll();
   ServerResponse selectByPrimaryKey(Integer poeid);
   ServerResponse modifyAll();

//   以下是管理员

   List<People> sysselectAll();
   int sysupdateStatus(Integer status, Integer id);
   int sysinsert(People people);

}
