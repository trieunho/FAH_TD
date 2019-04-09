package com.example.fah.FAHScreen.Models;

import java.util.Date;

public class Post {
    private String titlePost;
    private String account;
    private String companyName;
    private String address;
    private String luong;
    private String thoiGian;
    private Date deadLine;

    public Post(){}

    public Post(String titlePost, String account, String companyName) {
        this.titlePost = titlePost;
        this.account = account;
        this.companyName = companyName;
    }

    public Post(String titlePost, String companyName, String address, String thoiGian, String luong, Date deadLine) {
        this.titlePost = titlePost;
        this.companyName = companyName;
        this.address = address;
        this.thoiGian = thoiGian;
        this.luong = luong;
        this.deadLine = deadLine;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLuong() {
        return luong;
    }

    public void setLuong(String luong) {
        this.luong = luong;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }
}
