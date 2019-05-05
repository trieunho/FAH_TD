package com.example.fah.FAHCommon.FAHDatabase.Table;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.time.ZonedDateTime;

@RequiresApi(api = Build.VERSION_CODES.O)
public class FAHFieldCommon {
    private String key;
    private ZonedDateTime addDate = ZonedDateTime.now();

    public FAHFieldCommon() {
    }

    public FAHFieldCommon(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public ZonedDateTime getAddDate() {
        return addDate;
    }

    public void setAddDate(ZonedDateTime addDate) {
        this.addDate = addDate;
    }
}
