package com.yuanyin.leafnews.modules.semanticProcess.entity;

/**
 * Created by yuanyin on 16/5/25.
 */
public class Word implements Comparable {
    private String original;//单词内容
    private String pos;    //词性
    private String lemma;  //词根
    private int number;    //出现次数

    public Word() {
        number = 0;
    }

    public String getOriginal() {
        return original;
    }

    public void setOriginal(String original) {
        this.original = original;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public String getLemma() {
        return lemma;
    }

    public void setLemma(String lemma) {
        this.lemma = lemma;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void addNumber() {
        this.number++;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;

        Word word = (Word) o;

        return getLemma() != null ? getLemma().equals(word.getLemma()) : word.getLemma() == null;

    }

    @Override
    public int hashCode() {
        return getLemma() != null ? getLemma().hashCode() : 0;
    }

    @Override
    public int compareTo(Object o) {
        Word word = (Word) o;
        return word.number - this.number;
    }


}
