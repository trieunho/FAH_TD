package com.example.fah.FAHModel.Models;

public class Notification {
    private String notificationID;
    private String body;
    private String time;
    private String image;
    public String title;
    private String accountKey;
    private int screenId;

    public Notification(String notificationID, String body, String time, String image, String title, String accountKey, int screenId) {
        this.notificationID = notificationID;
        this.body = body;
        this.time = time;
        this.image = image;
        this.title = title;
        this.accountKey = accountKey;
        this.screenId = screenId;
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public Notification() {
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAccountKey() {
        return accountKey;
    }

    public void setAccountKey(String accountKey) {
        this.accountKey = accountKey;
    }
}
