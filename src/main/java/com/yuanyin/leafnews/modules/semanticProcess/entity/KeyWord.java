package com.yuanyin.leafnews.modules.semanticProcess.entity;

/**
 * Created by yuanyin on 16/5/25.
 */
public class KeyWord {
    private String content;  //关键词内容
    private double weight;      //关键词权重

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + content + "," + weight +")";
    }
}

