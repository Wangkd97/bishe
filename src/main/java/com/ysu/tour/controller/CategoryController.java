package com.ysu.tour.controller;

import com.ysu.tour.comment.Const;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.CategoryPic;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.IPictureService;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import com.ysu.tour.service.IUserActiveService;
import com.ysu.tour.viewobject.StrategyVO;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

    @Autowired
    IUserActiveService userActiveService;
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
    public ServerResponse selectbyid(Integer id,HttpSession session){

        UserInfo userInfo =(UserInfo) session.getAttribute(Const.CURRENT_USER);
        if (userInfo!=null){
            System.out.println("============");
        }
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
    @RequestMapping(value = "byuser.do",method = RequestMethod.POST)
    public ServerResponse byuser(Integer userId,Integer start){
       ServerResponse serverResponse = strategyService.selectAllByUserId(userId,start);
       if (serverResponse.getStatus()==0){
           return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
       }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
       }
    }

    @RequestMapping(value = "dobyuser.do",method = RequestMethod.POST)
    public ServerResponse dobyuser(Integer userId,Integer start){
        ServerResponse serverResponse = strategyService.selectDoByUserId(userId,start);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  serverResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @RequestMapping(value = "undobyuser.do",method = RequestMethod.POST)
    public ServerResponse undobyuser(Integer userId,Integer start){
        ServerResponse serverResponse = strategyService.selectUnDoByUserId(userId,start);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @RequestMapping(value = "countbyuser.do",method = RequestMethod.POST)
    public ServerResponse countbyuser(Integer userId){
        ServerResponse serverResponse = strategyService.selectcountallByUser(userId);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }
    @RequestMapping(value = "countIfDobyuser.do",method = RequestMethod.POST)
    public ServerResponse countbyuser(Integer userId,Integer status){
        ServerResponse serverResponse = strategyService.selectcountallIfDoByUser(userId,status);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @RequestMapping(value = "updateStatus.do",method = RequestMethod.POST)
    public ServerResponse updateStatus(Integer strategyId){
        ServerResponse serverResponse = strategyService.updateStatus(strategyId);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @RequestMapping(value = "updateStrategy.do",method = RequestMethod.POST)
    public ServerResponse updateStrategy(StrategyVO strategyVO){
        ServerResponse serverResponse = strategyService.updateStrategy(strategyVO);
        if (serverResponse.getStatus()==0){
            return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
        }else{
            return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
        }
    }

    @RequestMapping(value = "updateLookNum.do",method = RequestMethod.POST)
    public ServerResponse updateLookNum(Integer sId,HttpSession session){

        synchronized (this){
            UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENT_USER);
            if (userInfo!=null){
                userActiveService.updateLook(userInfo.getuId());
            }

            ServerResponse serverResponse = strategyService.updateLookNum(sId);
            if (serverResponse.getStatus()==0){
                return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "updateClickNum.do",method = RequestMethod.POST)
    public ServerResponse updateClickNum(Integer sId ,HttpSession session){
        synchronized (this){
            UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENT_USER);
            if (userInfo!=null){
                userActiveService.updateClick(userInfo.getuId());
            }
            ServerResponse serverResponse = strategyService.updateClickNum(sId);
            if (serverResponse.getStatus()==0){
                return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "updateCommentNum.do",method = RequestMethod.POST)
    public ServerResponse updateCommentNum(Integer sId,HttpSession session){
        synchronized (this){
            UserInfo userInfo = (UserInfo) session.getAttribute(Const.CURRENT_USER);
            if (userInfo!=null){
                userActiveService.updateComment(userInfo.getuId());
            }
            ServerResponse serverResponse = strategyService.updateCommentNum(sId);
            if (serverResponse.getStatus()==0){
                return  ServerResponse.createServerResponseBySucess(serverResponse.getData());
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "substractLookNum.do",method = RequestMethod.POST)
    public ServerResponse substractLookNum(Integer sId){
        synchronized (this){
            Integer value = strategyService.substractLookNum(sId);
            if (value!=0){
                return  ServerResponse.createServerResponseBySucess(value);
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "substractClickNum.do",method = RequestMethod.POST)
    public ServerResponse substractClickNum(Integer sId){
        synchronized (this){
            int value = strategyService.substractClickNum(sId);
            if (value!=0){
                return  ServerResponse.createServerResponseBySucess(value);
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "substractCommentNum.do",method = RequestMethod.POST)
    public ServerResponse substractCommentNum(Integer sId){
        synchronized (this){
            int value = strategyService.substractCommentNum(sId);
            if (value!=0){
                return  ServerResponse.createServerResponseBySucess(value);
            }else{
                return  ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"失败");
            }
        }

    }

    @RequestMapping(value = "getActive.do",method = RequestMethod.POST)
    public ServerResponse getActive( ){
        List<Category> list = strategyService.selectActiveTopFive();
       return ServerResponse.createServerResponseBySucess(list);

    }
}//end
