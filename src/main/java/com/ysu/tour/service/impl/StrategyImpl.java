package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.CategoryMapper;
import com.ysu.tour.dao.PeopleMapper;
import com.ysu.tour.dao.PlayMapper;
import com.ysu.tour.dao.SeasonMapper;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.pojo.People;
import com.ysu.tour.pojo.Play;
import com.ysu.tour.pojo.Season;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.service.IStrategyService;
import com.ysu.tour.viewobject.StrategyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public ServerResponse selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<StrategyVO> strategyVOS = new ArrayList<>();
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
        if (category!=null){
            return ServerResponse.createServerResponseBySucess(category);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"查询失败");
        }
    }
}
