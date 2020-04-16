package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;

public interface IPeopleService {

   ServerResponse selectAll();
   ServerResponse selectByPrimaryKey(Integer poeid);
   ServerResponse modifyAll();
}
