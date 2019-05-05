package com.example.fah.FAHScreen.Post;

public interface IOnButtonCLick {
    void onBtnApproveClick(int position);

    void onBtnDelClick(int position);

    void onItemClick(int position, String accountName, String key);
}
