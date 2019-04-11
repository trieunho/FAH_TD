package com.example.fah.FAHExcuteData.Models;

public class Notification {
    private String notificationID;
    private String notification;
    private String time;
    private int image;

    private Account account;

    public Notification(String notification, String time, int image) {
        this.notification = notification;
        this.time = time;
        this.image = image;
    }

    public String getNotification() {
        return notification;
    }

    public void setNotification(String notification) {
        this.notification = notification;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
