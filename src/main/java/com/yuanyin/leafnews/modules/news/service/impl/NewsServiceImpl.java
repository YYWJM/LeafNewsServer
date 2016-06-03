package com.yuanyin.leafnews.modules.news.service.impl;

import com.yuanyin.leafnews.modules.news.dao.NewsMapper;
import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyin on 16/5/23.
 */
@Service
public class NewsServiceImpl implements NewsService {
    @Resource
    private NewsMapper newsMapper;

    @Override
    public News getById(String id) {
        return newsMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<News> getAll() {
        return newsMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(News news) {
        newsMapper.updateByPrimaryKey(news);
    }
}
