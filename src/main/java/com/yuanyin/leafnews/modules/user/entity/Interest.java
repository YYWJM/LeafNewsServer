package com.yuanyin.leafnews.modules.user.entity;

/**
 * Created by yuanyin on 16/6/3.
 */
public class Interest {
    private String type;
    private double weight;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + type + "," + weight + ")";
    }
}
