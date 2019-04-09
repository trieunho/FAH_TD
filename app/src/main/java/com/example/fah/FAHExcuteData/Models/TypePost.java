package com.example.fah.FAHExcuteData.Models;

public class TypePost {
    private String nameTypePost;
    private Double moneyPost;
    private int timeShowPost;

    public TypePost(String nameTypePost, Double moneyPost, int timeShowPost) {
        this.nameTypePost = nameTypePost;
        this.moneyPost = moneyPost;
        this.timeShowPost = timeShowPost;
    }

    public String getNameTypePost() {
        return nameTypePost;
    }

    public void setNameTypePost(String nameTypePost) {
        this.nameTypePost = nameTypePost;
    }

    public Double getMoneyPost() {
        return moneyPost;
    }

    public void setMoneyPost(Double moneyPost) {
        this.moneyPost = moneyPost;
    }

    public int getTimeShowPost() {
        return timeShowPost;
    }

    public void setTimeShowPost(int timeShowPost) {
        this.timeShowPost = timeShowPost;
    }
}
