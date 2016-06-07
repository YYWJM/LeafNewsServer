package com.yuanyin.leafnews.modules.user.service.impl;

import com.yuanyin.leafnews.modules.user.dao.UserMapper;
import com.yuanyin.leafnews.modules.user.entity.Interest;
import com.yuanyin.leafnews.modules.user.entity.User;
import com.yuanyin.leafnews.modules.user.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by yuanyin on 16/6/3.
 */
@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getCurUser() {
        return getById("1");
    }

    @Override
    public User getById(String id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public void updateByPrimaryKey(User user) {
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 更新用户的兴趣模型,用户每次点击打开新闻详情页面都会调用这个函数
     *
     * @param type     用户阅读的新闻类型
     * @param readTime 用户的阅读时间
     */
    @Override
    public void updateInterestValue(String type, String readTime) {
        //用户合理的阅读时间最低为MIN_READ_TIME,最高为MAX_READ_TIME,将这个时间等分为三段
        double timeSlot1 = MIN_READ_TIME * 2 / 3 + MAX_READ_TIME / 3;
        double timeSlot2 = MIN_READ_TIME / 3 + MAX_READ_TIME * 2 / 3;
        //将用户实际的阅读时间与时间段进行匹配
        double time = Double.valueOf(readTime);
        double interestWeight;
        if (time < MIN_READ_TIME) {
            //如果实际阅读时间低于最低合理时间,则认为是误操作或不感兴趣
            interestWeight = 0;
        } else if (time < timeSlot1) {
            //如果实际阅读时间较短,则认为兴趣不浓
            interestWeight = 0.2;
        } else if (time < timeSlot2) {
            //如果实际阅读时间适中,则认为兴趣适中
            interestWeight = 0.6;
        } else if (time < MAX_READ_TIME) {
            //如果实际阅读时间较长,则认为用户很感兴趣
            interestWeight = 1;
        } else {
            //如果实际阅读时间超出最高合理时间,则认为用户去处理其他事情去了,并不在阅读
            interestWeight = 0;
        }
        //将处理得到的兴趣值加到哟欧诺个户该类别新闻的兴趣值中
        User user = getCurUser();
        List<Interest> interestList = user.getInterestList();
        for (Interest interest : interestList) {
            if (interest.getType().equals(type)) {
                interest.setWeight(interest.getWeight() + interestWeight);
            }
        }
        user.setInterest(interestList);
        //更新用户兴趣模型
        updateByPrimaryKey(user);
    }

}
