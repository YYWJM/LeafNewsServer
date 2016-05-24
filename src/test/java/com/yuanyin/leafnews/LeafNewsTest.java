package com.yuanyin.leafnews;

import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by yuanyin on 16/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class LeafNewsTest {
    @Resource
    private NewsService newsService;

    @Test
    public void test1() {
        News news = newsService.getById("1");
        System.out.println("--------------------------------" + news.toString());
    }

}
