package com.ysu.tour.dao;

import com.ysu.tour.pojo.Category;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoryMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_table
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer sId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_table
     *
     * @mbggenerated
     */
    int insert(Category record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_table
     *
     * @mbggenerated
     */
    Category selectByPrimaryKey(Integer sId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_table
     *
     * @mbggenerated
     */
    List<Category> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Category record);

    List<Category> fenyeselect(Integer start);
    int selectcountall();
}