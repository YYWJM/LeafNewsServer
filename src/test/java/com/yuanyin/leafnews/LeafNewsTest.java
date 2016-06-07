package com.yuanyin.leafnews;

import com.yuanyin.leafnews.modules.semanticProcess.service.CoreNLPService;
import com.yuanyin.leafnews.modules.news.entity.News;
import com.yuanyin.leafnews.modules.news.service.NewsService;
import com.yuanyin.leafnews.modules.user.entity.Interest;
import com.yuanyin.leafnews.modules.user.entity.User;
import com.yuanyin.leafnews.modules.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyin on 16/5/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext*.xml"})
public class LeafNewsTest {
    @Resource
    private NewsService newsService;

    @Test
    public void testNewsService() {
        News news = newsService.getById("1");
        System.out.println("--------------------------------" + news.toString());
    }

    @Resource
    private CoreNLPService coreNLPService;

    @Test
    public void testCoreNLPService(){
        coreNLPService.setAllKeyWord();
    }

    @Resource
    private UserService userService;

    @Test
    public void testUserService(){
        User user=userService.getById("1");
        System.out.println("--------------------------------" + user.toString());

        List<Interest> interestList=new ArrayList<>();
        for(int i=0;i<NewsService.TYPE_NUMBER;i++){
            Interest interest=new Interest();
            interest.setType(i+"");
            interest.setWeight(1);
            interestList.add(interest);
        }
        user.setInterest(interestList);
        userService.updateByPrimaryKey(user);
    }


    @Test
    public void test(){
        String str="000),(111";
        String[] arr=str.split("\\),\\(");
        for (String s : arr) {
            System.out.println(s);
        }
    }
}
