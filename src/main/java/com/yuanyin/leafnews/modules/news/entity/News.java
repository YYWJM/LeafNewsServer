package com.yuanyin.leafnews.modules.news.entity;

import java.util.Date;

public class News {
    private String docId;
    private String title;
    private String digest;
    private String body;
    private String imgsrc;
    private Date time;
    private String type;
    private String keyWord;
    private int isChoose;

    public String getDocId() {
        return docId;
    }

    public void setDocId(String docId) {
        this.docId = docId == null ? null : docId.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getDigest() {
        return digest;
    }

    public void setDigest(String digest) {
        this.digest = digest == null ? null : digest.trim();
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body == null ? null : body.trim();
    }

    public String getImgsrc() {
        return imgsrc;
    }

    public void setImgsrc(String imgsrc) {
        this.imgsrc = imgsrc == null ? null : imgsrc.trim();
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord == null ? null : keyWord.trim();
    }

    public int getIsChoose() {
        return isChoose;
    }

    public void setIsChoose(int isChoose) {
        this.isChoose = isChoose;
    }

    @Override
    public String toString() {
        return "News{" +
                "docId='" + docId + '\'' +
                ", title='" + title + '\'' +
                ", digest='" + digest + '\'' +
                ", body='" + body + '\'' +
                ", imgsrc='" + imgsrc + '\'' +
                ", time=" + time +
                ", type='" + type + '\'' +
                ", keyWord='" + keyWord + '\'' +
                ", isChoose=" + isChoose +
                '}';
    }
}