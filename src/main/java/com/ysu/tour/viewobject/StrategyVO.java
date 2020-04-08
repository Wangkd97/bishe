package com.ysu.tour.viewobject;

import java.util.List;

public class StrategyVO {

    public String getsTime() {
        return sTime;
    }

    private String sGotime;

    public String getsGotime() {
        return sGotime;
    }

    public void setsGotime(String sGotime) {
        this.sGotime = sGotime;
    }
    private Integer sId;  //攻略id
    private String sTime;   //攻略发布时间
    private String sName;   //攻略名称
    private String sCover;  //攻略封面图片
    private String sText;   //攻略内容
    private Integer sDay;   //游玩天数
    private Integer sPay;   //人均花费
    private Integer sStatus;    //攻略状态
    private Integer sComNum;    //攻略的评论数
    private Integer sCliNum;    //攻略的点击数
    private Integer sLookNum;   //攻略的浏览数
    private Integer sPicId;     //攻略中的图片
    private String sPeople;  //攻略中人物
    private List<String> sPlay;    //攻略的玩法
    private String sMasterName;
    private Integer sMasterId;
    private String sMasterUrl;

    public String getsMasterName() {
        return sMasterName;
    }

    public void setsMasterName(String sMasterName) {
        this.sMasterName = sMasterName;
    }

    public Integer getsMasterId() {
        return sMasterId;
    }

    public void setsMasterId(Integer sMasterId) {
        this.sMasterId = sMasterId;
    }

    public String getsMasterUrl() {
        return sMasterUrl;
    }

    public void setsMasterUrl(String sMasterUrl) {
        this.sMasterUrl = sMasterUrl;
    }

    private String sSeason;  //攻略的时节
    public Integer getsId() {
        return sId;
    }


    public void setsId(Integer sId) {
        this.sId = sId;
    }
    public void setsTime(String sTime) {
        this.sTime = sTime == null ? null : sTime.trim();
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }
    public String getsCover() {
        return sCover;
    }

    public void setsCover(String sCover) {
        this.sCover = sCover == null ? null : sCover.trim();
    }

    public String getsText() {
        return sText;
    }

    public void setsText(String sText) {
        this.sText = sText == null ? null : sText.trim();
    }

    public Integer getsDay() {
        return sDay;
    }

    public void setsDay(Integer sDay) {
        this.sDay = sDay;
    }

    public Integer getsPay() {
        return sPay;
    }

    public void setsPay(Integer sPay) {
        this.sPay = sPay;
    }

    public Integer getsStatus() {
        return sStatus;
    }

    public void setsStatus(Integer sStatus) {
        this.sStatus = sStatus;
    }

    public Integer getsComNum() {
        return sComNum;
    }

    public void setsComNum(Integer sComNum) {
        this.sComNum = sComNum;
    }

    public Integer getsCliNum() {
        return sCliNum;
    }

    public void setsCliNum(Integer sCliNum) {
        this.sCliNum = sCliNum;
    }

    public Integer getsLookNum() {
        return sLookNum;
    }

    public void setsLookNum(Integer sLookNum) {
        this.sLookNum = sLookNum;
    }

    public Integer getsPicId() {
        return sPicId;
    }

    public void setsPicId(Integer sPicId) {
        this.sPicId = sPicId;
    }

    public String getsPeople() {
        return sPeople;
    }

    public void setsPeople(String sPeopleId) {
        this.sPeople = sPeopleId;
    }

    public List<String> getsPlay() {
        return sPlay;
    }

    public void setsPlay(List<String> sPlayId) {
        this.sPlay = sPlayId;
    }

    public String getsSeason() {
        return sSeason;
    }

    public void setsSeason(String sSeasonId) {
        this.sSeason = sSeasonId;
    }
}
