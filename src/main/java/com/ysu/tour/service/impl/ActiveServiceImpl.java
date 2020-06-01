package com.ysu.tour.service.impl;

import com.ysu.tour.dao.ActiveMapper;
import com.ysu.tour.pojo.Active;
import com.ysu.tour.pojo.Category;
import com.ysu.tour.service.IActiveService;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActiveServiceImpl implements IActiveService {
   @Autowired
    ActiveMapper activeMapper;


    @Override
    public int insert(Active a) {
        int value = activeMapper.insert(a);
        return value;
    }

    @Override
    public List<Category> selectTopFive() {
        List<Category> list = activeMapper.selectTopFive();
        return list;
    }

    @Override
    public int sysUpdateStatus(Active a) {
        int value = activeMapper.sysUpdateStatus(a);
        return value;
    }

    @Override
    public List<Active> sysSelectTopTen() {
        List<Active> list = activeMapper.sysSelectTopTen();
        return list;
    }

    @Override
    public int delete() {
        int value=activeMapper.delete();
        return value;
    }
}
