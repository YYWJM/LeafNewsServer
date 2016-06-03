package com.yuanyin.leafnews.modules.news.service;

import com.yuanyin.leafnews.modules.news.entity.News;

import java.util.List;

/**
 * Created by yuanyin on 16/5/23.
 */
public interface NewsService {
    public News getById(String id);

    public List<News> getAll();

    public void updateByPrimaryKey(News news);
}
