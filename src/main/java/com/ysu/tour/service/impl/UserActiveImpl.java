package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.UserActiveMapper;
import com.ysu.tour.pojo.UserActive;
import com.ysu.tour.service.IUserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserActiveImpl implements IUserActiveService {

    @Autowired
    UserActiveMapper userActiveMapper;


    @Override
    public ServerResponse selectByuId(Integer uId) {
       UserActive userActive = userActiveMapper.selectByuId(uId);
       if (userActive!=null){
           return ServerResponse.createServerResponseBySucess(userActive);
       }else{
           return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
       }
    }

    @Override
    public ServerResponse updateClick(Integer uId) {
       int value = userActiveMapper.updateClick(uId);
       if (value>0){
           return ServerResponse.createServerResponseBySucess(value);
       }else{
           return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
       }
    }

    @Override
    public ServerResponse updateComment(Integer uId) {
        int value = userActiveMapper.updateComment(uId);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse updateLook(Integer uId) {
        int value = userActiveMapper.updateLook(uId);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse insert(Integer uId) {
        int value = userActiveMapper.insert(uId);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @Override
    public ServerResponse updateActive(Integer userActiveNum, Integer uId) {
        int value = userActiveMapper.updateActive(userActiveNum,uId);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
}
