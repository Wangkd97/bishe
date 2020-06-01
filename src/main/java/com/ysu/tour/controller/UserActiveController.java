package com.ysu.tour.controller;


import com.ysu.tour.comment.Const;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.service.IUserActiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController   //让返回值为字符串
@RequestMapping("/manage/userActive/")
@CrossOrigin
public class UserActiveController {

    @Autowired
    IUserActiveService iUserActiveService;

    @RequestMapping(value = "select.do",method = RequestMethod.POST)
    public ServerResponse select(Integer uId){

        ServerResponse value= iUserActiveService.selectByuId(uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }

    @RequestMapping(value = "updateClick.do",method = RequestMethod.POST)
    public ServerResponse updateClick(Integer uId){

        ServerResponse value= iUserActiveService.updateClick(uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }

    @RequestMapping(value = "updateComment.do",method = RequestMethod.POST)
    public ServerResponse updateComment(Integer uId){

        ServerResponse value= iUserActiveService.updateComment(uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }

    @RequestMapping(value = "updateLook.do",method = RequestMethod.POST)
    public ServerResponse updateLook(Integer uId){

        ServerResponse value= iUserActiveService.updateLook(uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }


    @RequestMapping(value = "updateActive.do",method = RequestMethod.POST)
    public ServerResponse updateClick(Integer userActiveNum,Integer uId){

        ServerResponse value= iUserActiveService.updateActive(userActiveNum,uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }


    @RequestMapping(value = "insert.do",method = RequestMethod.POST)
    public ServerResponse insert(Integer uId){

        ServerResponse value= iUserActiveService.insert(uId);
        if (value.getStatus()==100){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }else{
            return ServerResponse.createServerResponseBySucess(value.getData());
        }
    }

}//end
