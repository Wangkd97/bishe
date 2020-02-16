package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController   //让返回值为字符串
@RequestMapping("/manage/strategy/")
@CrossOrigin
public class CategoryController {

    @Autowired
    IStrategyService strategyService;
    ISeasonService seasonService;


    @RequestMapping(value = "strategy.do",method = RequestMethod.POST)
    public ServerResponse strategy(){
        ServerResponse serverResponse= strategyService.selectAll();
        if (serverResponse!=null){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }


    }
    @RequestMapping(value = "season.do",method = RequestMethod.POST)
    public ServerResponse season(Integer id){

        ServerResponse serverResponse=seasonService.find(id);

        if (serverResponse!=null){
            return ServerResponse.createServerResponseBySucess(serverResponse);

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }

}
