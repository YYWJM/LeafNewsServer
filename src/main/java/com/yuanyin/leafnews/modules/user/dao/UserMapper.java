package com.yuanyin.leafnews.modules.user.dao;

import com.yuanyin.leafnews.modules.user.entity.User;

/**
 * Created by yuanyin on 16/6/3.
 */
public interface UserMapper {
    
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}
