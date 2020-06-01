package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Active;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.service.IActiveService;
import com.ysu.tour.viewobject.SysActiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/manage/active/")
@CrossOrigin
public class ActiveController {

    @Autowired
    IActiveService iActiveService;
    @RequestMapping(value = "insert.do",method = RequestMethod.POST)
    public ServerResponse insert(Active active){
       int value = iActiveService.insert(active);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }


    }


    @RequestMapping(value = "sysActive.do",method = RequestMethod.POST)
    public ServerResponse sysActive(){
      List<Active> list = iActiveService.sysSelectTopTen();
      List<SysActiveVo> newlist  = new ArrayList<>();
        if (list.size()>0){
            int num=1;
            for (Active a:
                 list) {
                SysActiveVo sysActiveVo= new SysActiveVo();
                sysActiveVo.setKey(a.getsId());
                sysActiveVo.setsId(a.getsId());
                sysActiveVo.setLabel(a.getName());
                sysActiveVo.setDisable(a.getaStatus());
                newlist.add(sysActiveVo);
                num++;
            }
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }


    }

    @RequestMapping(value = "sysGetValue.do",method = RequestMethod.POST)
    public ServerResponse sysGetValue(){
        List<Active> list = iActiveService.sysSelectTopTen();
        List<Integer> newlist  = new ArrayList<>();
        if (list.size()>0){
            int num=1;
            for (Active a:
                    list) {
                if (a.getaStatus()==0){
                    newlist.add(a.getsId());
                }
                num++;
            }
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }


    }

    @RequestMapping(value = "selectChecked.do",method = RequestMethod.POST)
    public ServerResponse selectChecked(){
        List<Category> list = iActiveService.selectTopFive();
        if (list.size()>0){
            return ServerResponse.createServerResponseBySucess(list);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }


    }

    @RequestMapping(value = "sysUpdateStatus.do",method = RequestMethod.POST)
    public ServerResponse sysUpdateStatus(String movelist,String direction){
       String[] list =movelist.split(",");
        for (String s:
             list) {
            int id= Integer.parseInt(s);
            Active a= new Active();
            a.setsId(id);
            if (direction.equals("left")){//1
                a.setaStatus(1);
            }else{//0
                a.setaStatus(0);
            }
            int value =iActiveService.sysUpdateStatus(a);
            if (value==0){
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }
            return ServerResponse.createServerResponseBySucess(1);
    }


}
