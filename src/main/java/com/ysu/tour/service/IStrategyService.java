package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;
import org.apache.ibatis.annotations.Param;

public interface IStrategyService {

    ServerResponse selectAll();
    int insert(Category category);
    ServerResponse selectByPrimaryKey(Integer id);
    ServerResponse fenyeselect(Integer start);
    ServerResponse selectcountall();
    ServerResponse recommTags(String text);
    ServerResponse fenleiselect(String month,String day,String pay, String people, String play,int start);
    ServerResponse fuzzyselect(String name,int start);
    ServerResponse countfuzzyall(String name);
    ServerResponse countfenleiall(String month,String day,String pay, String people, String play);
    int insertPlay(int strategyId,int playId);
}
