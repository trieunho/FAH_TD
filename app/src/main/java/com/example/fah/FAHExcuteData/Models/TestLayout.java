package com.example.fah.FAHExcuteData.Models;

import android.content.Context;

public class TestLayout {
    Context packageContext;
    Class<?> cls;
    int idLayoutXml;
    String nameActivity;
    int typeActivity;
    IEvenDialog event;
     public static final int TYPE_DIALOG  = 2;

    public String getNameActivity() {
        return nameActivity;
    }

    public int getTypeActivity() {
        return typeActivity;
    }

    public void setTypeActivity(int typeActivity) {
        this.typeActivity = typeActivity;
    }

    public IEvenDialog getEvent() {
        return event;
    }

    public void setEvent(IEvenDialog event) {
        this.event = event;
    }

    public int getIdLayoutXml() {
        return idLayoutXml;
    }

    public void setIdLayoutXml(int idLayoutXml) {
        this.idLayoutXml = idLayoutXml;
    }

    public void setNameActivity(String nameActivity) {
        this.nameActivity = nameActivity;
    }

    public TestLayout(Context packageContext, Class<?> cls, String nameActivity) {
        this.packageContext = packageContext;
        this.cls = cls;
        this.nameActivity=nameActivity;
    }

    public TestLayout(Context packageContext, String nameActivity,int typeActivity, IEvenDialog event) {
        this.packageContext = packageContext;
        this.idLayoutXml = idLayoutXml;
        this.nameActivity=nameActivity;
        this.typeActivity=typeActivity;
        this.event = event;

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
