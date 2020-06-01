package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;

public interface IUserActiveService {

    ServerResponse selectByuId(Integer uId);
    ServerResponse updateClick(Integer uId);
    ServerResponse updateComment(Integer uId);
    ServerResponse updateLook(Integer uId);
    ServerResponse insert(Integer uId);
    ServerResponse updateActive(Integer userActiveNum,Integer uId);







}
