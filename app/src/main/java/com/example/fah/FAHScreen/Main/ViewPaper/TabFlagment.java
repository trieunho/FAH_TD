package com.example.fah.FAHScreen.Main.ViewPaper;

import android.support.v4.app.Fragment;

public class TabFlagment {
    private Fragment tabFlagment;
    private String title;
    private int icon;

    public TabFlagment(Fragment tabFlagment, String title, int icon) {
        this.tabFlagment = tabFlagment;
        this.title = title;
        this.icon = icon;
    }

    public Fragment getTabFlagment() {
        return tabFlagment;
    }

    public void setTabFlagment(Fragment tabFlagment) {
        this.tabFlagment = tabFlagment;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
