package com.example.fah.FAHScreen.Notification.model;

public class Account {
    private String accountName;
    private String email;
    private int sex;
    private int imgResource;

    public Account(String accountName, String email,int sex, int imgResource) {
        this.accountName = accountName;
        this.email = email;
        this.imgResource = imgResource;
        this.sex = sex;
    }

    public String getAccountName(){
        return accountName;
    }

    public void setAccountName(String accountName){
        this.accountName = accountName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getImgResource() {
        return imgResource;
    }

    public void setImgResource(int imgResource) {
        this.imgResource = imgResource;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
