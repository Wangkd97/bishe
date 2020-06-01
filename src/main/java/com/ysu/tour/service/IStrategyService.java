package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.viewobject.StrategyVO;

import java.util.List;

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
    ServerResponse selectAllByUserId(Integer userId,Integer start);
    ServerResponse selectDoByUserId(Integer userId,Integer start);
    ServerResponse selectUnDoByUserId(Integer userId,Integer start);
    ServerResponse selectcountallByUser(Integer userId);
    ServerResponse selectcountallIfDoByUser(Integer userId,Integer status);
    ServerResponse updateStatus(Integer strategyId);
    ServerResponse updateStrategy(StrategyVO category);

    ServerResponse updateLookNum(Integer sId);
    ServerResponse updateClickNum(Integer sId);
    ServerResponse updateCommentNum(Integer sId);
    int substractCommentNum(Integer sId);
    int substractLookNum(Integer sId);
    int substractClickNum(Integer sId);
    Category selectMasterBysId(Integer sId);

    Category selectActiveBysId(Integer sId);
    int updateActiveNum( Integer sActiveNum, Integer sId);

    List<Category> selectActiveTopFive();


   // ServerResponse updateStrategyPlay(Integer playId,Integer strategyId,Integer oldPlayId);
//    以下是管理员
    ServerResponse sysSelectAll(Integer start);
    ServerResponse sysUpdateStatus(Integer strategyId);
    ServerResponse sysSelectAllCount();
    ServerResponse sysSelectIfNot(Integer status,Integer start);
    ServerResponse sysSelectIfNotCount(Integer start);
    ServerResponse sysFuzzySelect(Integer status,Integer start,String name);
    ServerResponse syscountfuzzyall(Integer status,String name);

    List <Category>sysselectActiveTopTen();
}
