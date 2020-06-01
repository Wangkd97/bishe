package com.ysu.tour.pojo;

public class UserActive {

    Integer aId;
    Integer uId;
    Integer uClickNum;
    Integer uCommentNum;
    Integer uLookNum;
    Integer uActiveNum;

    public Integer getuActiveNum() {
        return uActiveNum;
    }

    public void setuActiveNum(Integer uActiveNum) {
        this.uActiveNum = uActiveNum;
    }

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public Integer getuClickNum() {
        return uClickNum;
    }

    public void setuClickNum(Integer uClickNum) {
        this.uClickNum = uClickNum;
    }

    public Integer getuCommentNum() {
        return uCommentNum;
    }

    public void setuCommentNum(Integer uCommentNum) {
        this.uCommentNum = uCommentNum;
    }

    public Integer getuLookNum() {
        return uLookNum;
    }

    public void setuLookNum(Integer uLookNum) {
        this.uLookNum = uLookNum;
    }
}
