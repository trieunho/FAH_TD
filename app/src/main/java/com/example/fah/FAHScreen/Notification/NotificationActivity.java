package com.example.fah.FAHScreen.Notification;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.fah.FAHScreen.Main.MainActivity;
import com.example.fah.FAHScreen.Adapters.NotificationAdapter;
import com.example.fah.FAHScreen.Models.Notification;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends AppCompatActivity {
    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        toolbar = findViewById(R.id.toolbarNotification);
        toolbar.setNavigationIcon(R.drawable.ic_chevron_left_black_24dp);

        toolbar.setTitle("Thông báo");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        List<Notification> notificationList = getListData();
        final ListView listView = (ListView) findViewById(R.id.notificationList);
        listView.setAdapter(new NotificationAdapter(this,R.layout.list_notification_item, notificationList));
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // noinspection SimplifiableIfStatement
        switch (item.getItemId()){
            case android.R.id.home: {
                startActivity(new Intent(NotificationActivity.this, MainActivity.class));
                return true;
            }
            default:{
                return super.onOptionsItemSelected(item);

            }
        }

    }

    private List<Notification> getListData() {
        List<Notification> list = new ArrayList<Notification>();
        Notification notification1 = new Notification("Công ty TBB vừa đăng thông tin ứng tuyển","5 phút trước",R.drawable.ic_account_circle_black_24dp);
        Notification notification2 = new Notification("Bạn có một thông báo mới từ công ty BTN","15 phút trước",R.drawable.ic_account_circle_black_24dp);
        Notification notification3 = new Notification("Bài đăng của bạn đã được duyệt thành công","45 phút trước",R.drawable.ic_account_circle_black_24dp);
        Notification notification4 = new Notification("Bài đăng của bạn đã được duyệt thành công","45 phút trước",R.drawable.ic_account_circle_black_24dp);
        Notification notification5 = new Notification("Bài đăng của bạn đã được duyệt thành công","45 phút trước",R.drawable.ic_account_circle_black_24dp);
        Notification notification6 = new Notification("Bài đăng của bạn đã được duyệt thành công","45 phút trước",R.drawable.ic_account_circle_black_24dp);

        list.add(notification1);
        list.add(notification2);
        list.add(notification3);
        list.add(notification4);
        list.add(notification5);
        list.add(notification6);

       return list;
    }
}
