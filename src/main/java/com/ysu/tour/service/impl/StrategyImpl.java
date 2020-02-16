package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.CategoryMapper;
import com.ysu.tour.dao.SeasonMapper;
import com.ysu.tour.pojo.Category;
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

    @Override
    public ServerResponse selectAll() {
        List<Category> categories = categoryMapper.selectAll();
        List<StrategyVO> strategyVOS = new ArrayList<>();
        if (categories!=null){
            for (Category cate:categories) {
                int season_id = cate.getsSeasonId();
               Season season = seasonMapper.selectById(season_id);   //获取该攻略的season
                //声明strategyvo
                StrategyVO strategyVO= new StrategyVO();
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
