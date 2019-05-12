package com.example.fah.FAHModel.Models;

import android.support.annotation.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notification implements Comparable<Notification>{
    private String notificationID;
    private String body;
    private String time;
    private String image;
    public String title;
    private String accountKey;
    private int screenId;

    public Notification(String notificationID, String body, String time, String image, String title, String accountKey, int screenId) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        String currentDateandTime = sdf.format(new Date());
        this.notificationID = notificationID;
        this.body = body;
        this.time = currentDateandTime.toString();
        this.image = image;
        this.title = title;
        this.accountKey = accountKey;
        this.screenId = screenId;
    }

    @Override
    public String toString() {
        return "Notification{" +
                "notificationID='" + notificationID + '\'' +
                ", body='" + body + '\'' +
                ", time='" + time + '\'' +
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

    @Override
    public int compareTo(@NonNull Notification notification) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
        Date strDate1 = null;
        Date strDate2 = null;
        try {
            strDate1 = sdf.parse(this.time.toString());
            strDate2 = sdf.parse(notification.time.toString());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (strDate1.after(strDate2)) {
            return 1;
        }else{
           if(strDate1.before(strDate2)){
               return -1;
           }else{
               return 0;
           }
        }
    }
}
