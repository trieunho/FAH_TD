package com.example.fah.FAHCommon.FAHDatabase.Table;

import java.util.Date;

public class FAHFieldCommon {
    private String key;
    private Date addDate = new Date();
    private Date updDate = new Date();

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

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public Date getUpdDate() {
        return updDate;
    }

    public void setUpdDate(Date updDate) {
        this.updDate = updDate;
    }
}
