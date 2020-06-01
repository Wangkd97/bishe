package com.ysu.tour.dao;

import com.ysu.tour.pojo.Comment;
import java.util.List;

public interface CommentMapper {

    int deleteByPrimaryKey(Integer cId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer cId);

    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    List<Comment> selectBysId(Integer sId);
    List <Comment> selectByccId(Integer ccId);
}