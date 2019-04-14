package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

public class TimeOfWork extends FAHFieldCommon {
    private String timeID;
    private String timeName;

    public String getTimeID() {
        return timeID;
    }

    public void setTimeID(String timeID) {
        this.timeID = timeID;
    }

    public String getTimeName() {
        return timeName;
    }

    public void setTimeName(String timeName) {
        this.timeName = timeName;
    }

    public TimeOfWork(String timeID, String timeName) {
        this.timeID = timeID;
        this.timeName = timeName;
    }

    public TimeOfWork() {
    }
}
