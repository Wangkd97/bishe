package com.ysu.tour.dao;

import com.ysu.tour.pojo.CategoryPic;
import java.util.List;

public interface CategoryPicMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_pic_table
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer spicId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_pic_table
     *
     * @mbggenerated
     */
    int insert(CategoryPic record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_pic_table
     *
     * @mbggenerated
     */
    CategoryPic selectByPrimaryKey(Integer spicId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_pic_table
     *
     * @mbggenerated
     */
    List<CategoryPic> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table strategy_pic_table
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(CategoryPic record);
    List<CategoryPic> selectBySId(Integer sId);
}