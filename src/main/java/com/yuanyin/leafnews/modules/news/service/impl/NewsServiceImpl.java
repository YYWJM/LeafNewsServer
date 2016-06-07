package com.yuanyin.leafnews.modules.news.service.impl;

import com.yuanyin.leafnews.modules.news.dao.NewsMapper;
import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import com.yuanyin.leafnews.modules.user.entity.Interest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

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

    /**
     * 按照用户兴趣模型各类型新闻的占比推荐新闻
     * 0:体育,1:时政,2:娱乐,3:经济,4:科技
     * @param interestList 用户兴趣列表
     * @param pageIndex    新闻列表翻页数
     * @return 推荐的新闻列表
     */
    @Override
    public List<News> getListByPage(List<Interest> interestList, int pageIndex) {
        List<News> newsList = new ArrayList<>();
        //若是加载新闻列表第一页,则对数据库中的新闻从头开始查询
        if (pageIndex == 0) {
            newsMapper.initChooseState();
        }
        //计算兴趣模型各新闻类型的兴趣值总和
        double sumWeight = 0;
        for (Interest interest : interestList) {
            sumWeight += interest.getWeight();
        }
        //获取每个类型的新闻
        for (Interest interest : interestList) {
            //计算该类型新闻在本次加载中分配的条数
            int number = (int) (interest.getWeight() / sumWeight * PAGE_SIZE);
            //从数据库中或得该类型的新闻信息
            List<News> typeNewsList = newsMapper.selectByType(number, interest.getType());
            //将该类型的新闻信息加入到本次加载的新闻列表中
            newsList.addAll(typeNewsList);
        }
        for (News news : newsList) {
            newsMapper.setChosen(news.getDocId());
        }
        //获取十条新闻的随机位置
        List<Integer> siteList = getNewsSite();
        //将新闻信息与随机得到的出现位置一一匹配
        final Map<News, Integer> siteMap = new HashMap<>();
        for (int i = 0; i < newsList.size(); i++) {
            siteMap.put(newsList.get(i), siteList.get(i));
        }
        //按照出现位置对新闻列表排序
        Collections.sort(newsList, new Comparator<News>() {
            @Override
            public int compare(News o1, News o2) {
                return siteMap.get(o1) - siteMap.get(o2);
            }
        });
        //返回推荐的新闻列表
        return newsList;
    }

    @Override
    public List<News> getAll() {
        return newsMapper.selectAll();
    }

    @Override
    public void updateByPrimaryKey(News news) {
        newsMapper.updateByPrimaryKey(news);
    }

    @Override
    public List<Integer> getNewsSite() {
        List<Integer> siteList = new ArrayList<>();
        Random random = new Random();
        while (siteList.size() < PAGE_SIZE) {
            int site = random.nextInt(PAGE_SIZE);
            if (!siteList.contains(site)) {
                siteList.add(site);
            }
        }
        return siteList;
    }

}
