package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.service.IPictureService;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/sys/strategy/")
@CrossOrigin
public class SysStrategyController {
    @Autowired
    IStrategyService strategyService;

    @Autowired
    ISeasonService seasonService;
    @Autowired
    IPictureService pictureService;

    @RequestMapping(value = "selectbyid.do",method = RequestMethod.POST)
    public ServerResponse selectbyid(Integer id){

        ServerResponse serverResponse=strategyService.selectByPrimaryKey(id);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "fenyeselect.do",method = RequestMethod.POST)
    public ServerResponse fenyeselect(Integer start){
        System.out.println("start=="+start);

        ServerResponse serverResponse= strategyService.fenyeselect(start);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "selectcountall.do",method = RequestMethod.POST)
    public ServerResponse selectcountall(){

        ServerResponse serverResponse= strategyService.sysSelectAllCount();

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "sysSelectAll.do",method = RequestMethod.POST)
    public ServerResponse sysSelectAll(Integer start){

        ServerResponse serverResponse= strategyService.sysSelectAll(start);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "sysUpdateStatus.do",method = RequestMethod.POST)
    public ServerResponse sysUpdateStatus(Integer strategyId){

        ServerResponse serverResponse= strategyService.sysUpdateStatus(strategyId);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "selectIfNotcountall.do",method = RequestMethod.POST)
    public ServerResponse selectIfNotcountall(Integer status){
        ServerResponse serverResponse= strategyService.sysSelectIfNotCount(status);
        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "sysSelectIfNotAll.do",method = RequestMethod.POST)
    public ServerResponse sysSelectNotAll(Integer status,Integer start){

        ServerResponse serverResponse= strategyService.sysSelectIfNot(status,start);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }

    @RequestMapping(value = "sysFuzzySelect.do",method = RequestMethod.POST)
    public ServerResponse sysFuzzySelect(Integer status,Integer start,String name){

        ServerResponse serverResponse= strategyService.sysFuzzySelect(status,start,name);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }

    @RequestMapping(value = "syscountfuzzyall.do",method = RequestMethod.POST)
    public ServerResponse syscountfuzzyall(Integer status,String name){

        ServerResponse serverResponse= strategyService.syscountfuzzyall(status,name);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "sysgetActive.do",method = RequestMethod.POST)
    public ServerResponse sysgetActivve(){
        List<Category> list = strategyService.sysselectActiveTopTen();

        for (Category c:
             list) {

        }

        return ServerResponse.createServerResponseBySucess(strategyService.sysselectActiveTopTen());

    }

}//end
