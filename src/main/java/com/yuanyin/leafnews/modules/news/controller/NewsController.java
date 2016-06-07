package com.yuanyin.leafnews.modules.news.controller;

import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import com.yuanyin.leafnews.modules.user.entity.User;
import com.yuanyin.leafnews.modules.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyin on 16/5/23.
 */
@Controller
@RequestMapping("/article")
public class NewsController {
    @Resource
    private NewsService newsService;
    @Resource
    private UserService userService;

    @RequestMapping("/detail/{docId}")
    @ResponseBody
    public News detail(@PathVariable("docId") String docId) {
        return newsService.getById(docId);
    }

    @RequestMapping("/list/{pageIndex}")
    @ResponseBody
    public List<News> list(@PathVariable("pageIndex") String pageIndex) {
        User user = userService.getCurUser();
        return newsService.getListByPage(user.getInterestList(), Integer.valueOf(pageIndex));
    }

}
