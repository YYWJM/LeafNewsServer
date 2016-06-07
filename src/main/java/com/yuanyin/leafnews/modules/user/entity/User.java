package com.yuanyin.leafnews.modules.user.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuanyin on 16/6/3.
 */
public class User {
    private String userId;
    private String name;
    private String gender;
    private String state;
    private String interest;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getInterest() {
        return interest;
    }

    public void setInterest(String interest) {
        this.interest = interest;
    }

    public List<Interest> getInterestList() {
        List<Interest> list = new ArrayList<>();
        String[] interestList = interest.substring(2, interest.length() - 2).split("\\), \\(");
        for (String str : interestList) {
            String[] pros = str.split(",");
            Interest interest = new Interest();
            interest.setType(pros[0]);
            interest.setWeight(Double.parseDouble(pros[1]));
            list.add(interest);
        }
        return list;
    }

    public void setInterest(List<Interest> interest) {
        this.interest = interest.toString();
    }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", state='" + state + '\'' +
                ", interest=" + interest +
                '}';
    }
}
