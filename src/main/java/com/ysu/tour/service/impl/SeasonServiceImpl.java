package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.SeasonMapper;
import com.ysu.tour.pojo.Season;
import com.ysu.tour.service.ISeasonService;
import com.ysu.tour.viewobject.TagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class SeasonServiceImpl implements ISeasonService {

    @Autowired
    SeasonMapper seasonMapper;

    @Override
    public ServerResponse selectAll() {
        List<Season> list = seasonMapper.selectAll();
        List<TagsVo>tagsVoList=new ArrayList<>();
        for (Season season:list)
        {
            TagsVo tagsVo=new TagsVo();
            tagsVo.setColor("");
            tagsVo.setId(season.getSeasonId());
            tagsVo.setLabel(season.getSeasonName());
            tagsVo.setType("");
            tagsVoList.add(tagsVo);
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(tagsVoList);
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }
    }

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
