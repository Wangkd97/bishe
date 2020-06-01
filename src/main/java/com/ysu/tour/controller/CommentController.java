package com.ysu.tour.controller;

import com.alibaba.druid.sql.visitor.functions.Concat;
import com.ysu.tour.comment.Const;
import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Comment;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController   //让返回值为字符串
@RequestMapping("/manage/comment/")
@CrossOrigin
public class CommentController {

    @Autowired
    ICommentService commentService;
    @RequestMapping(value = "getComment.do",method = RequestMethod.POST)
    public ServerResponse getComment(Integer sId){
        ServerResponse serverResponse = commentService.NewselectBysId(sId);
       return ServerResponse.createServerResponseBySucess(serverResponse.getData());
    }

    @RequestMapping(value = "insertComment.do",method = RequestMethod.POST)
    public ServerResponse insertComment(Comment comment){
        ServerResponse serverResponse = commentService.insert(comment);
        return ServerResponse.createServerResponseBySucess(serverResponse.getData());
    }

    @RequestMapping(value = "delectComment.do",method = RequestMethod.POST)
    public ServerResponse delectComment(Integer cId, HttpSession session){
        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENT_USER);
        if (userInfo==null){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"请先进行登录");
        }
        ServerResponse serverResponse = commentService.deleteByPrimaryKey(cId);
        return ServerResponse.createServerResponseBySucess(serverResponse.getData());
    }

//    以下是管理员
    @RequestMapping(value = "sysGetComment.do",method = RequestMethod.POST)
    public ServerResponse sysGetComment(Integer start, HttpSession session){
//        UserInfo userInfo= (UserInfo) session.getAttribute(Const.CURRENT_USER);
//        if (userInfo==null){
//            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"请先进行登录");
//        }
        ServerResponse serverResponse = commentService.selectStrategyComment(start);
        return ServerResponse.createServerResponseBySucess(serverResponse.getData());
    }
    @RequestMapping(value = "sysFuzzyGetComment.do",method = RequestMethod.POST)
    public ServerResponse sysFuzzyGetComment(String name,Integer start, HttpSession session){
        //默认状态为1
        ServerResponse serverResponse = commentService.sysFuzzyGetComment(name,start);
        return ServerResponse.createServerResponseBySucess(serverResponse.getData());
    }


}
