package com.example.fah.FAHModel.Models;

import java.util.ArrayList;

public class ListNotification {
    ArrayList<Notification> listNotification;

    public ListNotification(ArrayList<Notification> listNotification) {
        this.listNotification = listNotification;
    }

    public ListNotification() {
    }

    public ArrayList<Notification> getListNotification() {
        return listNotification;
    }

    public void setListNotification(ArrayList<Notification> listNotification) {
        this.listNotification = listNotification;
    }
}
