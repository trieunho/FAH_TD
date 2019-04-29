package com.example.fah.FAHScreen.Main.GridView.Menu;

import com.example.fah.FAHModel.Models.IEvenItem;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private String name;
    private String image;
    private IEvenItem eventClickItem;
    private String permission;

    public Menu(String name, String image, String permission) {
        this.name = name;
        this.image = image;
        this.permission = permission;
    }

    public Menu(String name, String image){
        this.name = name;
        this.image = image;
    }

    public IEvenItem getEventClickItem() {
        return eventClickItem;
    }

    public Menu(String name, String image, IEvenItem eventClickItem) {
        this.name = name;
        this.image = image;
        this.eventClickItem = eventClickItem;
    }

    public void setEventClickItem(IEvenItem eventClickItem) {
        this.eventClickItem = eventClickItem;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }
}
