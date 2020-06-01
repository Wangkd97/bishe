package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.CategoryMapper;
import com.ysu.tour.dao.CommentMapper;
import com.ysu.tour.dao.UserInfoMapper;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.Comment;
import com.ysu.tour.pojo.UserInfo;
import com.ysu.tour.service.ICommentService;
import com.ysu.tour.viewobject.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements ICommentService {

    @Autowired
    CommentMapper commentMapper;

    @Autowired
    CategoryMapper categoryMapper;

    @Autowired
    UserInfoMapper userInfoMapper;

    //废弃
//    @Override
//    public ServerResponse selectBysId(Integer sId) {
//       List<Comment> list =commentMapper.selectBysId(sId);
//       List<CommentVo>newlist =new ArrayList<>();
//
//       //从数据库中查出来第一层评论的人，查出该人的个人信息，然后再查询回复该用户的评论和评论的人
//       if (list.size()>0){
//           Category category=categoryMapper.selectMasterBysId(sId);
//           int value = category.getsMasterId();
//           for (Comment c: list) {
//               UserInfo userInfo = userInfoMapper.selectByPrimaryKey(c.getCuId());
//               if (c.getuId()==value){  //被评论人是主人
//                   CommentVo commentVo= new CommentVo();
//                   commentVo.setComment(c.getcText());
//                   commentVo.setuId(c.getCuId());
//                   commentVo.setHeadImg(userInfo.getuPic());
//                   commentVo.setName(userInfo.getuName());
//                   commentVo.setTime(c.getcTime());
//                   commentVo.setCommentNum(0);
//                   commentVo.setId(c.getcId());
//                   newlist.add(commentVo);
//               }
//           }
//           for (Comment c: list) {
//               UserInfo userInfo = userInfoMapper.selectByPrimaryKey(c.getCuId());//评论人
//               UserInfo userInfo1=userInfoMapper.selectByPrimaryKey(c.getuId());//被评论人
//               if (c.getuId()!=value){  //被评论人不是主人
//
//                   for (CommentVo cvo: newlist) {
//                       if (c.getuId()==cvo.getuId()){ //如果和第一层的相同
//                           cvo.setCommentNum(cvo.getCommentNum()+1);
//                           if (cvo.getReply()==null){ //如果rely是空，新建。
//                               List<CommentRelyVo> relylist = new ArrayList<>();
//                               CommentRelyVo commentVo = new CommentRelyVo();
//                               commentVo.setComment(c.getcText());
//                               commentVo.setuId(c.getCuId());
//                               commentVo.setTime(c.getcTime());
//                               commentVo.setCommentNum(0);
//                               commentVo.setFrom(userInfo.getuName());
//                               commentVo.setTo(userInfo1.getuName());
//                               commentVo.setFromId(userInfo.getuId());
//                               commentVo.setToId(userInfo1.getuId());
//                               commentVo.setFromHeadImg(userInfo.getuPic());
//                               relylist.add(commentVo);
//                               cvo.setReply(relylist);
//                           }else{ //如果rely不是空，直接添加
//                               List<CommentRelyVo> relylist =cvo.getReply();
//                               CommentRelyVo commentVo = new CommentRelyVo();
//                               commentVo.setComment(c.getcText());
//                               commentVo.setuId(c.getCuId());
//                               commentVo.setTime(c.getcTime());
//                               commentVo.setCommentNum(0);
//                               commentVo.setFrom(userInfo.getuName());
//                               commentVo.setTo(userInfo1.getuName());
//                               commentVo.setFromId(userInfo.getuId());
//                               commentVo.setToId(userInfo1.getuId());
//                               commentVo.setFromHeadImg(userInfo.getuPic());
//                               relylist.add(commentVo);
//                               cvo.setReply(relylist);
//                           }
//                       }else{ //和第一层的不同,rely肯定不是空了
//                           if(cvo.getReply()!=null){
//                               List<CommentRelyVo> diguilist= cvo.getReply();
//                               System.out.println("==="+diguilist);
//                               System.out.println("list[0]"+diguilist.get(0));
//                               System.out.println("list[0].name"+diguilist.get(0).getFrom());
////                               for (CommentRelyVo cc:diguilist) {
////                                   if (c.getuId()==cc.getFromId()){
////                                       cvo.setCommentNum(cvo.getCommentNum()+1);
////                                       CommentRelyVo commentVo = new CommentRelyVo();
////                                       commentVo.setComment(c.getcText());
////                                       commentVo.setuId(c.getCuId());
////                                       commentVo.setTime(c.getcTime());
////                                       commentVo.setCommentNum(0);
////                                       commentVo.setFrom(userInfo.getuName());
////                                       commentVo.setTo(userInfo1.getuName());
////                                       commentVo.setFromId(userInfo.getuId());
////                                       commentVo.setToId(userInfo1.getuId());
////                                       commentVo.setFromHeadImg(userInfo.getuPic());
////                                       diguilist.add(commentVo);
////                                   }
////                               }
//                               for(int i=0;i<diguilist.size();i++){
//                                   if (c.getuId()==diguilist.get(i).getFromId()){
//                                       cvo.setCommentNum(cvo.getCommentNum()+1);
//                                       CommentRelyVo commentVo = new CommentRelyVo();
//                                       commentVo.setComment(c.getcText());
//                                       commentVo.setuId(c.getCuId());
//                                       commentVo.setTime(c.getcTime());
//                                       commentVo.setCommentNum(0);
//                                       commentVo.setFrom(userInfo.getuName());
//                                       commentVo.setTo(userInfo1.getuName());
//                                       commentVo.setFromId(userInfo.getuId());
//                                       commentVo.setToId(userInfo1.getuId());
//                                       commentVo.setFromHeadImg(userInfo.getuPic());
//                                       diguilist.add(commentVo);
//                                   }
//                               }
//
//                           }
//
//                       }
//                   }
//               }
//           }
//       }
//       return ServerResponse.createServerResponseBySucess(newlist);
//    }

    @Override
    public ServerResponse NewselectBysId(Integer sId) {
        //数据库添加了一列，重新的想法。
        List<Comment> list = commentMapper.selectBysId(sId);
        List<CommentVo> newlist = new ArrayList<>();
        for (Comment c : list) {
            UserInfo userInfo = userInfoMapper.selectByPrimaryKey(c.getCuId());//评论人
            if (c.getCcId() == 0) { //如果是第一层的评论
                CommentVo commentVo = new CommentVo();
                commentVo.setComment(c.getcText());
                commentVo.setuId(c.getCuId());
                commentVo.setHeadImg(userInfo.getuPic());
                commentVo.setName(userInfo.getuName());
                commentVo.setTime(c.getcTime());
                commentVo.setCommentNum(0);
                commentVo.setId(c.getcId());
                newlist.add(commentVo);
            } else {

                UserInfo userInfo1 = userInfoMapper.selectByPrimaryKey(c.getuId());//被评论人
                for (CommentVo cc : newlist) {
                    if (c.getCcId() == cc.getId()) {
                        cc.setCommentNum(cc.getCommentNum()+1);
                        if (cc.getReply() == null) {
                            List<CommentRelyVo> relylist = new ArrayList<>();
                            CommentRelyVo commentVo = new CommentRelyVo();
                            commentVo.setComment(c.getcText());
                            commentVo.setTime(c.getcTime());
                            commentVo.setCommentNum(0);
                            commentVo.setFrom(userInfo.getuName());
                            commentVo.setTo(userInfo1.getuName());
                            commentVo.setFromId(userInfo.getuId());
                            commentVo.setToId(userInfo1.getuId());
                            commentVo.setFromHeadImg(userInfo.getuPic());
                            commentVo.setCcId(c.getCcId());
                            commentVo.setcId(c.getcId());
                            relylist.add(commentVo);
                            cc.setReply(relylist);
                        } else {
                            List<CommentRelyVo> relylist = cc.getReply();
                            CommentRelyVo commentVo = new CommentRelyVo();
                            commentVo.setComment(c.getcText());

                            commentVo.setTime(c.getcTime());
                            commentVo.setCommentNum(0);
                            commentVo.setFrom(userInfo.getuName());
                            commentVo.setTo(userInfo1.getuName());
                            commentVo.setFromId(userInfo.getuId());
                            commentVo.setToId(userInfo1.getuId());
                            commentVo.setFromHeadImg(userInfo.getuPic());
                            commentVo.setCcId(c.getCcId());
                            commentVo.setcId(c.getcId());
                            relylist.add(commentVo);
                            cc.setReply(relylist);
                        }
                    }
                }
            }
        }
        return ServerResponse.createServerResponseBySucess(newlist);
    }

    @Override
    public ServerResponse insert(Comment comment) {

        int value =commentMapper.insert(comment);
        return ServerResponse.createServerResponseBySucess(value);
    }

    @Override
    public ServerResponse deleteByPrimaryKey(Integer cId) {

        int value =commentMapper.deleteByPrimaryKey(cId);
        return ServerResponse.createServerResponseBySucess(value);
    }

    @Override
    public ServerResponse selectStrategyComment( Integer start) {
        //先查出来10条攻略，然后查攻略的评论，封装。
        List<SysStrategyVo> list = categoryMapper.sysSelectAll(start);
        List<SysCommentVo> newList = new ArrayList<>();
        if (list!=null){
            for (SysStrategyVo s:  //这是遍历10条攻略
                 list) {
                SysCommentVo sysCommentVo= new SysCommentVo();
                sysCommentVo.setStrategyName(s.getsName());
                Integer sId=s.getsId();
                List<Comment> commentslist=commentMapper.selectBysId(sId);
                List<SysCommentMainInfoVo> sysCommentMainInfoVoList = new ArrayList<>();
                for (Comment c:    //这是遍历评论
                     commentslist) {
                    UserInfo userInfo=userInfoMapper.selectByPrimaryKey(c.getuId());  //这个是评论用户，from
                    UserInfo userInfo1=userInfoMapper.selectByPrimaryKey(c.getCuId()); //这个是被评论用户，to
                    SysCommentMainInfoVo sysCommentMainInfoVo= new SysCommentMainInfoVo();
                    sysCommentMainInfoVo.setcId(c.getcId());
                    sysCommentMainInfoVo.setComment(c.getcText());
                    sysCommentMainInfoVo.setFromName(userInfo.getuName());
                    sysCommentMainInfoVo.setToName(userInfo1.getuName());
                    sysCommentMainInfoVo.setTime(c.getcTime());
                    sysCommentMainInfoVoList.add(sysCommentMainInfoVo);

                }
                sysCommentVo.setList(sysCommentMainInfoVoList);
                newList.add(sysCommentVo);
            }

        }
        return ServerResponse.createServerResponseBySucess(newList);
    }

    @Override
    public ServerResponse sysFuzzyGetComment(String name, Integer start) {
        String newname="%"+name+"%";
        List<Category> list = categoryMapper.fuzzyselect(newname,start);
        List<SysCommentVo> newList = new ArrayList<>();
        if (list!=null){
            for (Category s:  //这是遍历10条攻略
                    list) {
                SysCommentVo sysCommentVo= new SysCommentVo();
                sysCommentVo.setStrategyName(s.getsName());
                Integer sId=s.getsId();
                List<Comment> commentslist=commentMapper.selectBysId(sId);
                List<SysCommentMainInfoVo> sysCommentMainInfoVoList = new ArrayList<>();
                for (Comment c:    //这是遍历评论
                        commentslist) {
                    UserInfo userInfo=userInfoMapper.selectByPrimaryKey(c.getuId());  //这个是评论用户，from
                    UserInfo userInfo1=userInfoMapper.selectByPrimaryKey(c.getCuId()); //这个是被评论用户，to
                    SysCommentMainInfoVo sysCommentMainInfoVo= new SysCommentMainInfoVo();
                    sysCommentMainInfoVo.setcId(c.getcId());
                    sysCommentMainInfoVo.setComment(c.getcText());
                    sysCommentMainInfoVo.setFromName(userInfo.getuName());
                    sysCommentMainInfoVo.setToName(userInfo1.getuName());
                    sysCommentMainInfoVo.setTime(c.getcTime());
                    sysCommentMainInfoVoList.add(sysCommentMainInfoVo);

                }
                sysCommentVo.setList(sysCommentMainInfoVoList);
                newList.add(sysCommentVo);
            }

        }
        return ServerResponse.createServerResponseBySucess(newList);

    }


}
