package com.ysu.tour.viewobject;

import java.util.List;

public class CommentVo {
    String  name;//评论的用户名
    Integer id;//评论id
    String headImg;//评论的用户头像
    String comment;//评论
    String time;//时间
    Integer commentNum;//回复评论的数量
    Integer like;//待定
    boolean inputShow;//:false,//待定
    Integer uId;//被评论的用户id
    List<CommentRelyVo> reply;//回复的评论



    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getCommentNum() {
        return commentNum;
    }

    public void setCommentNum(Integer commentNum) {
        this.commentNum = commentNum;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public boolean isInputShow() {
        return inputShow;
    }

    public void setInputShow(boolean inputShow) {
        this.inputShow = inputShow;
    }

    public List<CommentRelyVo> getReply() {
        return reply;
    }

    public void setReply(List<CommentRelyVo> reply) {
        this.reply = reply;
    }
}
