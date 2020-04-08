package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.*;
import com.ysu.tour.pojo.*;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import com.ysu.tour.viewobject.StrategyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.Response;
import java.util.ArrayList;
import java.util.List;

@Service
public class StrategyImpl implements IStrategyService {
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    SeasonMapper seasonMapper;
    @Autowired
    PeopleMapper peopleMapper;
    @Autowired
    PlayMapper playMapper;
    @Autowired
    UserInfoMapper userInfoMapper;
    @Override
    public ServerResponse selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<StrategyVO> strategyVOS= new ArrayList<>();
        return strategyVo(categories,strategyVOS);

    }

    @Override
    public ServerResponse insert(Category category) {
        if (category.getsName()==null||category.getsName().equals("")){
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"攻略名称为空");
        }else {

           int value =categoryMapper.insert(category);
           if (value==1){
               return ServerResponse.createServerResponseBySucess("添加成功");

           }else{
               return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"添加失败");
           }
        }
    }

    @Override
    public ServerResponse selectByPrimaryKey(Integer id) {
       Category category = categoryMapper.selectByPrimaryKey(id);
       UserInfo userInfo=userInfoMapper.selectByPrimaryKey(category.getsMasterId());
       StrategyVO strategyVO=new StrategyVO();
        int season_id = category.getsSeasonId();
        Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
        People people=peopleMapper.selectByPrimaryKey(category.getsPeopleId()); //获取该攻略的人物
        List<Play> list = playMapper.selectById(category.getsId());
        List<String> stringList= new ArrayList<>();
        if(list!=null){
            for (Play p:list) {
                stringList.add(p.getPlayName());
            }
        }
        strategyVO.setsMasterId(category.getsMasterId());
        strategyVO.setsMasterUrl(userInfo.getuPic());
        strategyVO.setsMasterName(userInfo.getuName());
        strategyVO.setsId(category.getsId());
        strategyVO.setsCover(category.getsCover());
        strategyVO.setsText(category.getsText());
        strategyVO.setsPlay(stringList);
        strategyVO.setsPeople(people.getPeoName());
        strategyVO.setsCliNum(category.getsCliNum());
        strategyVO.setsComNum(category.getsComNum());
        strategyVO.setsDay(category.getsDay());
        strategyVO.setsGotime(category.getsGotime());
        strategyVO.setsName(category.getsName());
        strategyVO.setsLookNum(category.getsLookNum());
        strategyVO.setsPay(category.getsPay());
        if (season!=null)
            strategyVO.setsSeason(season.getSeasonName());
        strategyVO.setsTime(category.getsTime());

        if (category!=null){
            return ServerResponse.createServerResponseBySucess(strategyVO);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"查询失败");
        }
    }

    @Override
    public ServerResponse fenyeselect(Integer start) {
        List <Category>categories =  categoryMapper.fenyeselect(start);


        List<StrategyVO> strategyVOS=new ArrayList<>();
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                if(list!=null)
                for (Play p:list) {
                    stringList.add(p.getPlayName());
                }
                UserInfo userInfo=userInfoMapper.selectByPrimaryKey(cate.getsMasterId());

                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();

                strategyVO.setsMasterId(cate.getsMasterId());
                strategyVO.setsMasterUrl(userInfo.getuPic());
                strategyVO.setsMasterName(userInfo.getuName());
                strategyVO.setsId(cate.getsId());
                strategyVO.setsCover(cate.getsCover());
                strategyVO.setsText(cate.getsText());
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                if (season!=null)
                strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }

    }

    @Override
    public ServerResponse selectcountall() {
       int size = categoryMapper.selectcountall();
        return ServerResponse.createServerResponseBySucess(size);
    }

    ;
    public ServerResponse strategyVo( List<Category> categories,List<StrategyVO> strategyVOS){
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
                Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                People people=peopleMapper.selectByPrimaryKey(cate.getsPeopleId()); //获取该攻略的人物
                List<Play> list = playMapper.selectById(cate.getsId());
                List<String> stringList= new ArrayList<>();
                for (Play p:list) {
                    stringList.add(p.getPlayName());
                }
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
                strategyVO.setsPlay(stringList);
                strategyVO.setsPeople(people.getPeoName());
                strategyVO.setsCliNum(cate.getsCliNum());
                strategyVO.setsComNum(cate.getsComNum());
                strategyVO.setsDay(cate.getsDay());
                strategyVO.setsGotime(cate.getsGotime());
                System.out.println(strategyVO.getsGotime()+"=========");
                System.out.println(cate.getsGotime()+"**********");
                strategyVO.setsName(cate.getsName());
                strategyVO.setsLookNum(cate.getsLookNum());
                strategyVO.setsPay(cate.getsPay());
                strategyVO.setsSeason(season.getSeasonName());
                strategyVO.setsTime(cate.getsTime());
                System.out.println(cate.getsTime()+"=====time=====");
                strategyVOS.add(strategyVO);
            }
            return ServerResponse.createServerResponseBySucess(strategyVOS);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无攻略");
        }
    }
}
