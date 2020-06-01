package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.UserInfoMapper;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IUserService;
import com.ysu.tour.viewobject.UserInfoVo;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {


    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public ServerResponse login(UserInfo userInfo) {

        UserInfo userInfo3 = userInfoMapper.selectStatusByEmail(userInfo.getuEmail());
        if (userInfo3.getuStatus()==2){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"封号状态");
        }
        if(userInfo.getuEmail()==null||userInfo.getuEmail().equals("")){
          // "用户名不能为空");  100代表用户名为空。
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"用户名不能为空");
        }
        if(userInfo.getuPwd()==null||userInfo.getuPwd().equals("")){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"密码不能为空");
        }
        UserInfo userInfo1=userInfoMapper.selectByUserNameAndPwd(userInfo);

        return ServerResponse.createServerResponseBySucess(userInfo1);
    }

    @Override
    public ServerResponse insert(UserInfo userInfo) {

        //下面没有判断必须上传头像
        if (userInfo.getuEmail()==null||userInfo.getuEmail().equals("")||userInfo.getuPwd()==null||
                userInfo.getuPwd().equals("")||userInfo.getuName()==null||userInfo.getuName().equals("")||
                userInfo.getuAge()==null||userInfo.getuAge().equals("")||userInfo.getuGender()==null||
                userInfo.getuGender().equals("")){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"用户信息填写不能为空");
        }else{
            int value = userInfoMapper.insert(userInfo);
            if (value>0){
                return  ServerResponse.createServerResponseBySucess(ResponseCode.SUCCESS,"创建成功");
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"创建失败");
            }
        }

    }

    @Override
    public ServerResponse selectAll() {
        List<UserInfo> list =userInfoMapper.selectAll();
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(list);

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无用户");
        }
    }

    @Override
    public ServerResponse sysSelectAll() {
        List<UserInfo>list = userInfoMapper.sysSelectAll();
        return ServerResponse.createServerResponseBySucess(list);
    }

    @Override
    public ServerResponse updateByPrimaryKey(UserInfo userInfo) {
        if (userInfo!=null){
            int value = userInfoMapper.updateByPrimaryKey(userInfo);
            if (value>0){
                return ServerResponse.createServerResponseBySucess(userInfo);
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse updatePwd(UserInfo userInfo) {
        if (userInfo!=null){
            int value = userInfoMapper.updatePwd(userInfo);
            if (value>0){
                return ServerResponse.createServerResponseBySucess(userInfo);
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }

    }

    @Override
    public ServerResponse selectStatusByEmail(String Email){
        UserInfo userInfo = userInfoMapper.selectStatusByEmail(Email);
        if (userInfo!=null){
            return ServerResponse.createServerResponseBySucess(userInfo);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public UserInfo selectByPrimaryKey(Integer uId) {
        UserInfo userInfo  = userInfoMapper.selectByPrimaryKey(uId);
        return userInfo;
    }


    //    以下是管理员
    @Override
    public ServerResponse sysfenyeselectAll(Integer start) {
        List<UserInfo> list=userInfoMapper.sysfenyeselectAll(start);
        if (list.size()!=0){
            List<UserInfoVo> newlsit = new ArrayList<>();
            for (UserInfo u :
                    list) {
                UserInfoVo userInfoVo= new UserInfoVo();
                userInfoVo.setuAge(u.getuAge());
                userInfoVo.setuEmail(u.getuEmail());
                if (u.getuGender()==1){
                    userInfoVo.setuGender("男");
                }else{
                    userInfoVo.setuGender("女");
                }
                userInfoVo.setuId(u.getuId());
                userInfoVo.setuName(u.getuName());
                userInfoVo.setuPic(u.getuPic());
                if (u.getuStatus()==0){
                    userInfoVo.setuStatus("正常");
                }else if(u.getuStatus()==2){
                    userInfoVo.setuStatus("封号");
                }
                newlsit.add(userInfoVo);
            }
            return  ServerResponse.createServerResponseBySucess(newlsit);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse sysfenyeselectAllCount() {
        int value = userInfoMapper.sysfenyeselectAllCount();
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse sysupdateStatus(Integer status, Integer uId) {
       int value = userInfoMapper.sysupdateStatus(status,uId);
       if (value>0){
           return  ServerResponse.createServerResponseBySucess(value);
       }else{
           return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
       }
    }

    @Override
    public ServerResponse updateById(UserInfo userInfo) {
        if (userInfo!=null){
            int value = userInfoMapper.updateById(userInfo);
            if (value>0){
                return ServerResponse.createServerResponseBySucess(userInfo);
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public ServerResponse sysselectByEmail(String uEmail) {
        UserInfo userInfo=userInfoMapper.sysselectByEmail(uEmail);
        if (userInfo!=null){
            return ServerResponse.createServerResponseBySucess(userInfo);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @Override
    public UserInfo sysselectByEmailandPwd(String uEmail,String uPwd){
        UserInfo userInfo= userInfoMapper.sysselectByEmailandPwd(uEmail,uPwd);
       return userInfo;

    }
}
