package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.PlayMapper;
import com.ysu.tour.pojo.Play;
import com.ysu.tour.service.IPlayService;
import com.ysu.tour.viewobject.TagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PlayServiceImpl implements IPlayService {

    @Autowired
    PlayMapper playMapper;

    @Override
    public ServerResponse selectAll() {
        List<Play> list = playMapper.selectAll();
        List<TagsVo> tagsVoList = new ArrayList<>();
        for (Play play:list)
        {
            TagsVo tagsVo=new TagsVo();
            tagsVo.setColor("");
            tagsVo.setId(play.getPlayId());
            tagsVo.setLabel(play.getPlayName());
            tagsVo.setType("");
            tagsVoList.add(tagsVo);
        }
        if (list!=null){
            return ServerResponse.createServerResponseBySucess(tagsVoList);
        }else{
            return  ServerResponse.createServerResponseByFail("未找到");
        }

    }

    @Override
    public ServerResponse modifyAll() {
        List<Play> list = playMapper.selectAll();
        List<TagsVo> tagsVoList = new ArrayList<>();
        TagsVo tagsVo= new TagsVo();
        tagsVo.setColor("#efd576");
        tagsVo.setId(-1);
        tagsVo.setLabel("全部");
        tagsVo.setType("");
        tagsVoList.add(tagsVo);

        if (list!=null){
            for (Play play:list)
            {
                TagsVo tagsVo2=new TagsVo();
                tagsVo2.setColor("");
                tagsVo2.setId(play.getPlayId());
                tagsVo2.setLabel(play.getPlayName());
                tagsVo2.setType("");
                tagsVoList.add(tagsVo2);
            }
            return ServerResponse.createServerResponseBySucess(tagsVoList);
        }else{
            return  ServerResponse.createServerResponseByFail("未找到");
        }

    }
}
