package com.ysu.tour.service;

import com.ysu.tour.comment.ServerResponse;
import com.ysu.tour.pojo.Comment;

import java.util.List;

public interface ICommentService {

   // ServerResponse selectBysId(Integer sId);
    //List<Comment> selectByccId(Integer ccId); //这个不用实现，直接在serviceImpl中用了

    ServerResponse NewselectBysId(Integer sId);

    ServerResponse insert(Comment comment);

    ServerResponse deleteByPrimaryKey(Integer cId);

    ServerResponse selectStrategyComment(Integer start);
    ServerResponse sysFuzzyGetComment(String name,Integer start);
}
