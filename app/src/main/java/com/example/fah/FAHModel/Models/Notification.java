package com.example.fah.FAHModel.Models;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

import java.util.Calendar;

public class Notification extends FAHFieldCommon implements Comparable<Notification>{
    private String notificationID;
    private String body;
    private String image;
    public String title;
    private String accountKey;
    private int screenId;

    public Notification(String notificationID, String body, String time, String image, String title, String accountKey, int screenId) {
        this.notificationID = notificationID;
        this.body = body;
        this.image = image;
        this.title = title;
        this.accountKey = accountKey;
        this.screenId = screenId;
        this.setAddDate(Calendar.getInstance().getTime());
        this.setUpdDate(Calendar.getInstance().getTime());
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationID='" + notificationID + '\'' +
                ", body='" + body + '\'' +
                ", image='" + image + '\'' +
                ", title='" + title + '\'' +
                ", accountKey='" + accountKey + '\'' +
                ", screenId=" + screenId +
                '}';
    }

    public int getScreenId() {
        return screenId;
    }

    public void setScreenId(int screenId) {
        this.screenId = screenId;
    }

    public Notification() {}

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

    @Override
    public int compareTo(@NonNull Notification notification) {
        if(this.getAddDate()!=null && notification.getAddDate()!=null){
            if (this.getAddDate().after(notification.getAddDate())) {
                return 1;
            }else{
                if(this.getAddDate().before(notification.getAddDate())){
                    return -1;
                }else{
                    return 0;
                }
            }
        }else{
            return 0;
        }
    }
}
