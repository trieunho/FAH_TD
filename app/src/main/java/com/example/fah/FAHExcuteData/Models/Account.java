package com.example.fah.FAHExcuteData.Models;

public class Account {
    private String accountName;
    private String email;
    private int sex;
    private int statusBlock;

    public Account(String accountName, String email, int sex, int statusBlock) {
        this.accountName = accountName;
        this.email = email;
        this.statusBlock = statusBlock;
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

    public int getStatusBlock() {
        return statusBlock;
    }

    public void setstatusBlock(int statusBlock) {
        this.statusBlock = statusBlock;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }
}
