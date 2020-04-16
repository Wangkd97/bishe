package com.ysu.tour.controller;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.IPeopleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController   //让返回值为字符串
@RequestMapping("/manage/strategy/people/")
@CrossOrigin
public class PeopleController {
    @Autowired
    IPeopleService peopleService;

    @RequestMapping(value = "getallpeople.do",method = RequestMethod.POST)
    public ServerResponse getallpeople(){
        ServerResponse serverResponse= peopleService.selectAll();
        if (serverResponse.getData()!=null){  //status = 1 是为找到.
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }

    }
    @RequestMapping(value = "getmodifypeople.do",method = RequestMethod.POST)
    public ServerResponse getmodifypeople(){
        ServerResponse serverResponse= peopleService.modifyAll();
        if (serverResponse.getData()!=null){  //status = 1 是为找到.
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }

    }

}
