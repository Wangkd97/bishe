package com.ysu.tour.controller;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.IPlayService;
import com.ysu.tour.service.impl.PlayServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController   //让返回值为字符串
@RequestMapping("/manage/strategy/play/")
@CrossOrigin
public class PlayController {
    @Autowired
    IPlayService playService;


    @RequestMapping(value = "getallplay.do",method = RequestMethod.POST)
    public ServerResponse getallplay(){
        ServerResponse serverResponse= playService.selectAll();
        if (serverResponse.getData()!=null){  //status = 1 是为找到.
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }

    }
    @RequestMapping(value = "getmodifyplay.do",method = RequestMethod.POST)
    public ServerResponse getmodifyplay(){
        ServerResponse serverResponse= playService.modifyAll();
        if (serverResponse.getData()!=null){  //status = 1 是为找到.
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }

    }


}//end
