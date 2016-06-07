package com.yuanyin.leafnews.modules.user.controller;

import com.yuanyin.leafnews.modules.user.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * Created by yuanyin on 16/6/3.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @RequestMapping("/readTime")
    public void setReadTime(@RequestParam("type") String type, @RequestParam("readTime") String readTime) {
        userService.updateInterestValue(type, readTime);
    }
}
