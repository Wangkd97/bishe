package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.PeopleMapper;
import com.ysu.tour.pojo.People;
import com.ysu.tour.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleServiceImpl implements IPeopleService {


    @Autowired
    PeopleMapper peopleMapper;


    @Override
    public ServerResponse selectByPrimaryKey(Integer peoid) {

        People p=peopleMapper.selectByPrimaryKey(peoid);
        if (p!=null){
            return ServerResponse.createServerResponseBySucess(p);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无该人物");
        }

    }
}
