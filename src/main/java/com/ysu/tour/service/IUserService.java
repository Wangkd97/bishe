package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.UserInfo;
import org.apache.catalina.User;

public interface IUserService {

    ServerResponse login (UserInfo userInfo) ;
    ServerResponse insert(UserInfo userInfo);
    ServerResponse selectAll();


}
