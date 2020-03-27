package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Category;

public interface IStrategyService {

    ServerResponse selectAll();
    ServerResponse insert(Category category);
    ServerResponse selectByPrimaryKey(Integer id);
}
