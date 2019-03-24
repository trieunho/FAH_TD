package com.example.fah.FAHScreen.Models;

public class Notification {
    private String Notification;
    private String Time;
    private int Image;

    public Notification(String notification, String time, int image) {
        Notification = notification;
        Time = time;
        Image = image;
    }

    public String getNotification() {
        return Notification;
    }

    public void setNotification(String notification) {
        Notification = notification;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        Time = time;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }
}
