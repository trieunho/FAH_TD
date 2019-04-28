package com.example.fah.FAHModel.Models;

import com.example.fah.FAHCommon.FAHDatabase.Table.FAHFieldCommon;

public class Category extends FAHFieldCommon {

    private String categoryID;
    private String categoryName;

    public Category() {}

    public Category(String categoryID, String categoryName) {
        this.categoryID = categoryID;
        this.categoryName = categoryName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }


    public String getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(String categoryID) {
        this.categoryID = categoryID;
    }
}
