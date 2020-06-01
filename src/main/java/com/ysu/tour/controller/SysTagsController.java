package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.People;
import com.ysu.tour.pojo.Play;
import com.ysu.tour.pojo.Season;
import com.ysu.tour.service.IPeopleService;
import com.ysu.tour.service.IPlayService;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.viewobject.SysActiveVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/sys/tags/")
@CrossOrigin
public class SysTagsController {

    @Autowired
    IPeopleService iPeopleService;
    @Autowired
    IPlayService iPlayService;
    @Autowired
    ISeasonService iSeasonService;

//people
    @RequestMapping(value = "peopleinsert.do",method = RequestMethod.POST)
    public ServerResponse peopleinsert(People people){

      int value = iPeopleService.sysinsert(people);
      if (value>0){
          return ServerResponse.createServerResponseBySucess(value);
      }else{
          return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
      }
    }
    @RequestMapping(value = "peopleupdatestatus.do",method = RequestMethod.POST)
    public ServerResponse peopleupdatestatus(String movelist,String direction){
        String[] list =movelist.split(",");
        int value=-1;
        for (String s:
             list) {
            int id = Integer.parseInt(s);
            if (direction.equals("left")){
                int status=1;
                 value = iPeopleService.sysupdateStatus(status,id);
            }else{
                int status=0;
                value=iPeopleService.sysupdateStatus(status,id);
            }
        }

        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
    @RequestMapping(value = "peopleselectAll.do",method = RequestMethod.POST)
    public ServerResponse peopleselectAll(){
        List<People> list = iPeopleService.sysselectAll();
        List<SysActiveVo> newlist = new ArrayList<>();
        for (People p:list
                ) {
            SysActiveVo sysActiveVo= new SysActiveVo();
            sysActiveVo.setKey(p.getPeoId());
            sysActiveVo.setsId(p.getPeoId());
            sysActiveVo.setLabel(p.getPeoName());
            newlist.add(sysActiveVo);
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
    @RequestMapping(value = "peoplegetvalue.do",method = RequestMethod.POST)
    public ServerResponse peoplegetvalue(){
        List<People> list = iPeopleService.sysselectAll();
        List<Integer> newlist = new ArrayList<>();
        for (People p:list
                ) {
         if (p.getPeoStatus().equals("0"))
            newlist.add(p.getPeoId());
            System.out.println(p.getPeoId());
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

//play
    @RequestMapping(value = "playinsert.do",method = RequestMethod.POST)
    public ServerResponse playinsert(Play play){
    int value = iPlayService.sysinsert(play);
    if (value>0){
        return ServerResponse.createServerResponseBySucess(value);
    }else{
        return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
    }
}
    @RequestMapping(value = "playupdatestatus.do",method = RequestMethod.POST)
    public ServerResponse playupdatestatus(Integer status,Integer id){
        int value = iPlayService.sysupdateStatus(status,id);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
    @RequestMapping(value = "playselectAll.do",method = RequestMethod.POST)
    public ServerResponse playselectAll(){
        List<Play> list = iPlayService.sysselectAll();
        List<SysActiveVo> newlist = new ArrayList<>();
        for (Play p:list
                ) {
            SysActiveVo sysActiveVo= new SysActiveVo();
            sysActiveVo.setKey(p.getPlayId());
            sysActiveVo.setsId(p.getPlayId());
            sysActiveVo.setLabel(p.getPlayName());
            newlist.add(sysActiveVo);
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }


//season
    @RequestMapping(value = "seasoninsert.do",method = RequestMethod.POST)
    public ServerResponse seasoninsert(Season season){
    int value =iSeasonService.sysinsert(season);
    if (value>0){
        return ServerResponse.createServerResponseBySucess(value);
    }else{
        return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
    }
}
    @RequestMapping(value = "seasonupdatestatus.do",method = RequestMethod.POST)
    public ServerResponse seasonupdatestatus(Integer status,Integer id){
        int value = iSeasonService.sysupdateStatus(status,id);
        if (value>0){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
    @RequestMapping(value = "seaseonselectAll.do",method = RequestMethod.POST)
    public ServerResponse seasonselectAll(){
        List<Season> list = iSeasonService.sysselectAll();
        List<SysActiveVo> newlist = new ArrayList<>();
        for (Season p:list
                ) {
            SysActiveVo sysActiveVo= new SysActiveVo();
            sysActiveVo.setKey(p.getSeasonId());
            sysActiveVo.setsId(p.getSeasonId());
            sysActiveVo.setLabel(p.getSeasonName());
            newlist.add(sysActiveVo);
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(newlist);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }







}//end
