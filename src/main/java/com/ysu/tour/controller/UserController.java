package com.ysu.tour.controller;

import com.ysu.tour.comment.Const;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController   //让返回值为字符串
@RequestMapping("/manage/user/")
@CrossOrigin
public class UserController {
    @Autowired
    IUserService userService;


    @RequestMapping(value = "login.do",method = RequestMethod.POST)
    public ServerResponse login(UserInfo userInfo, HttpSession session){

       ServerResponse loginUserInfo=userService.login(userInfo);

        if(loginUserInfo!=null){
            session.setAttribute(Const.CURRENT_USER,loginUserInfo);
        }
        if (loginUserInfo.getData()!=null){
            return ServerResponse.createServerResponseBySucess(loginUserInfo.getData());
        }else{
           return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,loginUserInfo.getMsg());
        }
      //  return ServerResponse.createServerResponseByFail(11,"123");
    }

    @RequestMapping(value = "insert.do",method = RequestMethod.POST)
    public ServerResponse insert(UserInfo userInfo, HttpSession session){

        ServerResponse insertuser=userService.insert(userInfo);
            if (insertuser.getStatus()==ResponseCode.SUCCESS){
                return ServerResponse.createServerResponseBySucess(insertuser.getData());
            }else{
                return ServerResponse.createServerResponseByFail(insertuser.getMsg());
            }
    }
    @RequestMapping(value = "selectAll.do",method = RequestMethod.POST)
    public ServerResponse selectAll(){

        ServerResponse alluser=userService.selectAll();
        if (alluser.getStatus()==ResponseCode.SUCCESS||alluser!=null){
            return ServerResponse.createServerResponseBySucess(alluser.getData());
        }else{
            return ServerResponse.createServerResponseByFail(alluser.getMsg());
        }
    }


}
