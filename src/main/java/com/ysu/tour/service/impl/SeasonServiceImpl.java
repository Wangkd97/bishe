package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.SeasonMapper;
import com.ysu.tour.pojo.Season;
import com.ysu.tour.service.ISeasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class SeasonServiceImpl implements ISeasonService {

    @Autowired
    SeasonMapper seasonMapper;

    @Override
    public ServerResponse find(Integer id) {

        Season season = seasonMapper.selectById(id);
        if (season.equals(null)||season!=null)
        {
            return ServerResponse.createServerResponseBySucess(season.getSeasonName());
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"时节不存在");
        }

    }
}
