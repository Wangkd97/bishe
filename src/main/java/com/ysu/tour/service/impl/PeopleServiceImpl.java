package com.ysu.tour.service.impl;

import com.ysu.tour.comment.ResponseCode;
import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.dao.PeopleMapper;
import com.ysu.tour.pojo.People;
import com.ysu.tour.service.IPeopleService;
import com.ysu.tour.viewobject.SysActiveVo;
import com.ysu.tour.viewobject.TagsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PeopleServiceImpl implements IPeopleService {


    @Autowired
    PeopleMapper peopleMapper;


    @Override
    public ServerResponse selectAll() {
        List<People> list = peopleMapper.selectAll();
        List<TagsVo> tagsVoList = new ArrayList<>();
        for (People people:list)
        {
            TagsVo tagsVo=new TagsVo();
            tagsVo.setColor("");
            tagsVo.setId(people.getPeoId());
            tagsVo.setLabel(people.getPeoName());
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
    public ServerResponse selectByPrimaryKey(Integer peoid) {

        People p=peopleMapper.selectByPrimaryKey(peoid);
        if (p!=null){
            return ServerResponse.createServerResponseBySucess(p);
        }else{
            return ServerResponse.createServerResponseByFail(ResponseCode.ERROR,"无该人物");
        }

    }

    @Override
    public ServerResponse modifyAll() {
        List<People> list = peopleMapper.selectAll();
        List<TagsVo> tagsVoList = new ArrayList<>();
       TagsVo aa = new TagsVo();
       aa.setColor("#efd576");
       aa.setId(-1);
       aa.setLabel("全部");
       aa.setType("");
       tagsVoList.add(aa);
        if (list!=null){
            for (People people:list)
            {
                TagsVo tagsVo=new TagsVo();
                tagsVo.setColor("");
                tagsVo.setId(people.getPeoId());
                tagsVo.setLabel(people.getPeoName());
                tagsVo.setType("");
                tagsVoList.add(tagsVo);
            }
            return ServerResponse.createServerResponseBySucess(tagsVoList);
        }else{
            return ServerResponse.createServerResponseByFail("未找到");
        }
    }



//    以下是管理员

    @Override
    public List<People> sysselectAll() {
       List<People> list = peopleMapper.sysselectAll();

       return list;
    }

    @Override
    public int sysupdateStatus(Integer status, Integer id) {
       int value = peopleMapper.sysupdateStatus(status,id);
        return value;
    }

    @Override
    public int sysinsert(People people) {
        int value = peopleMapper.sysinsert(people);
        return value;
    }



}//end
