package com.example.fah.FAHScreen.Models;

public class Post {
    private String titlePost;
    private String account;
    private String companyName;

    public Post(String titlePost, String account, String companyName) {
        this.titlePost = titlePost;
        this.account = account;
        this.companyName = companyName;
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
}
