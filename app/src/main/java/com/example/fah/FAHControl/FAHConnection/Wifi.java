package com.example.fah.FAHControl.FAHConnection;

import android.app.Application;

public class Wifi extends Application {
    static Wifi wifiInstance;
    @Override
    public void onCreate() {
        super.onCreate();
        wifiInstance = this;
    }
    public static synchronized Wifi getInstance() {
        return wifiInstance;
    }
}
