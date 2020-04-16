package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.ISeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController   //让返回值为字符串
@RequestMapping("/manage/strategy/season/")
@CrossOrigin
public class SeasonController {

    @Autowired
    ISeasonService seasonService;
    @RequestMapping(value = "getallseason.do",method = RequestMethod.POST)
    public ServerResponse getallseason(){
        ServerResponse serverResponse= seasonService.selectAll();
        if (serverResponse.getData()!=null){  //status = 1 是为找到.
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }

    }
}
