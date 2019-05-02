package com.example.fah.FAHModel.Models;

public class Notification {
    private String notificationID;
    private String notification;
    private String time;
    private String image;

    private Account account;

    public Notification(String notification, String time, String image) {
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
