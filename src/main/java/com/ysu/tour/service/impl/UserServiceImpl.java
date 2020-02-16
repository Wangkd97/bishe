package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.UserInfoMapper;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public ServerResponse login(UserInfo userInfo) {

        if(userInfo.getuName()==null||userInfo.getuName().equals("")){
          // "用户名不能为空");  100代表用户名为空。
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"用户名不能为空");
        }
        if(userInfo.getuPwd()==null||userInfo.getuPwd().equals("")){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"密码不能为空");
        }
        UserInfo userInfo1=new UserInfo();

        userInfo1=userInfoMapper.selectByUserNameAndPwd(userInfo);

        return ServerResponse.createServerResponseBySucess(userInfo1);
    }

    @Override
    public ServerResponse insert(UserInfo userInfo) {

        //下面没有判断必须上传头像
        if (userInfo.getuEmail()==null||userInfo.getuEmail().equals("")||userInfo.getuPwd()==null||
                userInfo.getuPwd().equals("")||userInfo.getuName()==null||userInfo.getuName().equals("")||
                userInfo.getuAge()==null||userInfo.getuAge().equals("")||userInfo.getuGender()==null||
                userInfo.getuGender().equals("")){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"用户信息填写不能为空");
        }else{
            int value = userInfoMapper.insert(userInfo);
            if (value>0){
                return  ServerResponse.createServerResponseBySucess(ResponseCode.SUCCESS,"创建成功");
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"创建失败");
            }
        }

    }


}
