package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/sys/user/")
@CrossOrigin
public class SysUserController {
    @Autowired
    IUserService userService;

    //    以下是管理员
    @RequestMapping(value = "sysfenyeselectAll.do",method = RequestMethod.POST)
    public ServerResponse sysfenyeselectAll(Integer start){
        ServerResponse alluser=userService.sysfenyeselectAll(start);
        if (alluser.getStatus()!= ResponseCode.ERROR){
            return ServerResponse.createServerResponseBySucess(alluser.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }
    @RequestMapping(value = "sysfenyeselectAllCount.do",method = RequestMethod.POST)
    public ServerResponse sysfenyeselectAllCount(){
        ServerResponse alluser=userService.sysfenyeselectAllCount();
        if (alluser.getStatus()!=ResponseCode.ERROR){
            return ServerResponse.createServerResponseBySucess(alluser.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }
    @RequestMapping(value = "sysupdateStatus.do",method = RequestMethod.POST)
    public ServerResponse sysupdateStatus(Integer status,Integer uId){
        ServerResponse alluser=userService.sysupdateStatus(status,uId);
        if (alluser.getStatus()!=ResponseCode.ERROR){
            return ServerResponse.createServerResponseBySucess(alluser.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @RequestMapping(value = "sysselectByEmail.do",method = RequestMethod.POST)
    public ServerResponse sysselectByEmail(String uEmail){
        ServerResponse alluser=userService.sysselectByEmail(uEmail);
        List<UserInfo> list = new ArrayList<>();
        list.add((UserInfo) alluser.getData());
        if (alluser.getStatus()!=ResponseCode.ERROR){
            return ServerResponse.createServerResponseBySucess(list);

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse login(String uEmail,String uPwd){
        UserInfo userInfo = userService.sysselectByEmailandPwd(uEmail,uPwd);
        if (userInfo!=null){
            return ServerResponse.createServerResponseBySucess(userInfo);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
}//end
