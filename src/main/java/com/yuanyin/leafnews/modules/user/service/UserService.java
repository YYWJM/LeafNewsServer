package com.yuanyin.leafnews.modules.user.service;

import com.yuanyin.leafnews.modules.user.entity.User;

import java.util.List;

/**
 * Created by yuanyin on 16/6/3.
 */
public interface UserService {
    public static final long MIN_READ_TIME = 1000;
    public static final long MAX_READ_TIME = 30000;

    User getById(String id);

    void updateByPrimaryKey(User user);

    void updateInterestValue(String type, String readTime);

    User getCurUser();

}
