package com.example.fah.FAHExcuteData.Models;

public class SampleAccount  {

    private String name,email;
    private  int resourceImg;

    public SampleAccount() {
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

    public SampleAccount(String name, String email, int resourceImg) {
        this.name = name;
        this.email = email;
        this.resourceImg = resourceImg;
    }
}
