package com.example.fah.FAHScreen.Models;

import android.content.Context;

public class TestLayout {
    Context packageContext;
    Class<?> cls;
    String nameActivity;

    public String getNameActivity() {
        return nameActivity;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public TestLayout(Context packageContext, Class<?> cls, String nameActivity) {
        this.packageContext = packageContext;
        this.cls = cls;
        this.nameActivity=nameActivity;
    }

    public Context getPackageContext() {
        return packageContext;
    }

    public void setPackageContext(Context packageContext) {
        this.packageContext = packageContext;
    }

    public Class<?> getCls() {
        return cls;
    }

    public void setCls(Class<?> cls) {
        this.cls = cls;
    }
}
