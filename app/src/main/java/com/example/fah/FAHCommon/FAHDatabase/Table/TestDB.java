package com.example.fah.FAHCommon.FAHDatabase.Table;

import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.TimeOfWork;
import com.example.fah.FAHModel.Models.TypeOfPost;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestDB extends FAHFieldCommon{
    private String titlePost;
    private ArrayList<Account> listOfAccApply;

    public TestDB(){}

    public TestDB(String titlePost, ArrayList<Account> listOfAccApply) {
        this.titlePost = titlePost;
        this.listOfAccApply = listOfAccApply;
    }

    public String getTitlePost() {
        return titlePost;
    }

    public void setTitlePost(String titlePost) {
        this.titlePost = titlePost;
    }

    public List<Account> getListOfAccApply() {
        return listOfAccApply;
    }

    public void setListOfAccApply(ArrayList<Account> listOfAccApply) {
        this.listOfAccApply = listOfAccApply;
    }
}
