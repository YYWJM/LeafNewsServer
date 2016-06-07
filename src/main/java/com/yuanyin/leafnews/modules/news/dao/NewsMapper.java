package com.yuanyin.leafnews.modules.news.dao;

import com.yuanyin.leafnews.modules.news.entity.News;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface NewsMapper {

    int deleteByPrimaryKey(String docId);

    int insert(News record);

    int insertSelective(News record);

    News selectByPrimaryKey(String docId);

    List<News> selectByType(@Param("rows") int rows, @Param("type") String type);

    List<News> selectAll();

    int updateByPrimaryKeySelective(News record);

    int updateByPrimaryKey(News record);

    int setChosen(String docId);

    int initChooseState();
}