package com.ysu.tour.viewobject;

public class CommentRelyVo {
    String from;//  评论人的名字   :'Taylor Swift',
    Integer fromId;//评论人的id:19891221,
    String fromHeadImg;//评论人的头像:'https://ae01.alicdn.com/kf/H94c78935ffa64e7e977544d19ecebf06L.jpg',
    String to;//被评论人的名字:'Lana Del Rey',
    Integer toId;//被评论人的id:19870621,
    String comment;//评论:'我很喜欢你的新专辑！！',
    String time;//评论的时间:'2019年9月16日 18:43',
    Integer commentNum;//评论的数目:1,
    Integer like;//待定
    boolean inputShow;//待定:false;//
    Integer cId;//评论的id
    Integer ccId;  //所在评论的层数

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getCcId() {
        return ccId;
    }

    public void setCcId(Integer ccId) {
        this.ccId = ccId;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public Integer getFromId() {
        return fromId;
    }

    public void setFromId(Integer fromId) {
        this.fromId = fromId;
    }

    public String getFromHeadImg() {
        return fromHeadImg;
    }

    public void setFromHeadImg(String fromHeadImg) {
        this.fromHeadImg = fromHeadImg;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Integer getToId() {
        return toId;
    }

    public void setToId(Integer toId) {
        this.toId = toId;
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

    public Integer getcId() {
        return cId;
    }

}
