package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.UserInfo;
import org.apache.catalina.User;

public interface IUserService {

    ServerResponse login (UserInfo userInfo) ;
    ServerResponse insert(UserInfo userInfo);
    ServerResponse selectAll();
    ServerResponse sysSelectAll();
    ServerResponse updateByPrimaryKey(UserInfo userInfo);
    ServerResponse updatePwd(UserInfo userInfo);
    ServerResponse selectStatusByEmail(String Email);
    UserInfo selectByPrimaryKey(Integer uId);

    UserInfo sysselectByEmailandPwd(String uEmail,String uPwd);



//    以下是管理员
    ServerResponse sysfenyeselectAll(Integer start);
    ServerResponse sysfenyeselectAllCount();
    ServerResponse sysupdateStatus(Integer status,Integer uId);

    //插入数据用
    ServerResponse updateById(UserInfo userInfo);
    ServerResponse sysselectByEmail(String uEmail);

}
