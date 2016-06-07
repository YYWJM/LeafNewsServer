package com.yuanyin.leafnews.modules.news.service;

import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.user.entity.Interest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by yuanyin on 16/5/23.
 */
public interface NewsService {
    //一次加载新闻的条数
    public static final int PAGE_SIZE = 10;
    //新闻类型数量
    public static final int TYPE_NUMBER = 5;

    News getById(String id);

    List<News> getListByPage(List<Interest> interestList, int pageIndex);

    List<News> getAll();

    void updateByPrimaryKey(News news);

    List<Integer> getNewsSite();
}
