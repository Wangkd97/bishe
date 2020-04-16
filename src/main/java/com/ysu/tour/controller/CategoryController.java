package com.ysu.tour.controller;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.CategoryPic;
import com.ysu.tour.service.IPictureService;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/manage/strategy/")
@CrossOrigin
public class CategoryController {

    @Autowired
    IStrategyService strategyService;

    @Autowired
    ISeasonService seasonService;
    @Autowired
    IPictureService pictureService;
    @RequestMapping(value = "strategy.do",method = RequestMethod.POST)
    public ServerResponse strategy(){
        ServerResponse serverResponse= strategyService.selectAll();
        if (serverResponse!=null){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }


    }
    @RequestMapping(value = "season.do",method = RequestMethod.POST)
    public ServerResponse season(Integer id){

        ServerResponse serverResponse=seasonService.find(id);

        if (serverResponse!=null){
            return ServerResponse.createServerResponseBySucess(serverResponse);

        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }

    @RequestMapping(value = "strategyinsert.do",method = RequestMethod.POST)
    public ServerResponse strategyinsert(Category category){
       int sid=strategyService.insert(category);
       List<String>piclist =pictureService.getListPic(category.getsText());
        for (String s:piclist) {
            CategoryPic categoryPic=new CategoryPic();
            categoryPic.setsId(sid);
            categoryPic.setSpicUrl(s);
            pictureService.insert(categoryPic);
        }
        int playinsert = strategyService.insertPlay(sid,category.getsPlayId());
        if (sid>=0&&playinsert>0){
            return ServerResponse.createServerResponseBySucess(1);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"添加失败");
        }
    }
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
    public ServerResponse fenyeselect( Integer start){

        ServerResponse serverResponse= strategyService.fenyeselect(start);

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "selectcountall.do",method = RequestMethod.POST)
    public ServerResponse selectcountall(){

        ServerResponse serverResponse= strategyService.selectcountall();

        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "recommTags.do",method = RequestMethod.POST)
    public ServerResponse recommTags(String content){

        if (content!=null||content.equals("")||content.equals(null)){

            ServerResponse serverResponse= strategyService.recommTags(content);
            if (serverResponse.getStatus()==0){
                return ServerResponse.createServerResponseBySucess(serverResponse.getData());
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
            }

        }else{
            return ServerResponse.createServerResponseByFail("文本为空" );
        }
    }
    @RequestMapping(value = "fenleiselect.do",method = RequestMethod.POST)
    public ServerResponse fenleiselect(String month,String day,String pay,String people,String play,int start){
            ServerResponse serverResponse= strategyService.fenleiselect(month, day, pay, people, play,start);
            if (serverResponse.getStatus()==0){
                return ServerResponse.createServerResponseBySucess(serverResponse.getData());
            }else{
                return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
            }
    }
    @RequestMapping(value = "fuzzyselect.do",method = RequestMethod.POST)
    public ServerResponse fuzzyselect(String name,int start){
        ServerResponse serverResponse= strategyService.fuzzyselect(name,start);
        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "countfuzzyselect.do",method = RequestMethod.POST)
    public ServerResponse countfuzzyselect(String name){
        ServerResponse serverResponse= strategyService.countfuzzyall(name);
        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "countfenleiall.do",method = RequestMethod.POST)
    public ServerResponse countfenleiall(String month,String day,String pay,String people,String play){
        ServerResponse serverResponse= strategyService.countfenleiall(month, day, pay, people, play);
        if (serverResponse.getStatus()==0){
            return ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,serverResponse.getMsg());
        }
    }
    @RequestMapping(value = "insertplay.do",method = RequestMethod.POST)
    public ServerResponse insertplay(int strategyId,int playId){
       int value= strategyService.insertPlay(strategyId,playId);
        if (value==1){
            return ServerResponse.createServerResponseBySucess(value);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

}//end
