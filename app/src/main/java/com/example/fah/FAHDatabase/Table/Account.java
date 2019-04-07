package com.example.fah.FAHDatabase.Table;

public class Account extends FAHFieldCommon{
    private String name,email;
    private int resourceImg;

    public Account() {
    }

    public Account(String name, String email, int resourceImg) {
        this.name = name;
        this.email = email;
        this.resourceImg = resourceImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getResourceImg() {
        return resourceImg;
    }

    public void setResourceImg(int resourceImg) {
        this.resourceImg = resourceImg;
    }
}
