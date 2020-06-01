package com.ysu.tour.controller;

import com.ysu.tour.comment.Const;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import org.apache.catalina.User;
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
            session.setAttribute(Const.CURRENT_USER,loginUserInfo.getData());
        }
        if (loginUserInfo.getStatus()==ResponseCode.ERROR){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,loginUserInfo.getMsg());

        }else if(loginUserInfo.getData()!=null){
            return ServerResponse.createServerResponseBySucess(loginUserInfo.getData());
        }
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"账号或密码错误");

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
    @RequestMapping(value = "sysSelectAll.do",method = RequestMethod.POST)
    public ServerResponse sysSelectAll(){

        ServerResponse alluser=userService.sysSelectAll();
        if (alluser.getData()!=null){
            return ServerResponse.createServerResponseBySucess(alluser.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    public ServerResponse update(UserInfo userInfo){
        ServerResponse value=userService.updateByPrimaryKey(userInfo);
        if (value.getData()!=null){
            return ServerResponse.createServerResponseBySucess(value.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }
    @RequestMapping(value = "updatePwd.do",method = RequestMethod.POST)
    public ServerResponse updatePwd(UserInfo userInfo){
        ServerResponse value=userService.updatePwd(userInfo);
        if (value.getData()!=null){
            return ServerResponse.createServerResponseBySucess(value.getData());

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @RequestMapping(value = "getUser.do",method = RequestMethod.POST)
    public ServerResponse getUser(HttpSession session){
      UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENT_USER);
      if (userInfo!=null){
         return  ServerResponse.createServerResponseBySucess(userInfo);
      } else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @RequestMapping(value = "exit.do",method = RequestMethod.POST)
    public ServerResponse exit(HttpSession session){
       session.setAttribute(Const.CURRENT_USER,null);
        if (session.getAttribute(Const.CURRENT_USER)==null){
            return  ServerResponse.createServerResponseBySucess("成功退出");
        } else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

//    以下是管理员
    @RequestMapping(value = "sysfenyeselectAll.do",method = RequestMethod.POST)
        public ServerResponse sysfenyeselectAll(Integer start){
        ServerResponse alluser=userService.sysfenyeselectAll(start);
        if (alluser.getStatus()!=ResponseCode.ERROR){
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





}//end
