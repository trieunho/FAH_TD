package com.example.fah.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.fah.FAHScreen.Account.ManageAccountByAdminActivity;
import com.example.fah.FAHScreen.Account.ManageAccountByPostActivity;
import com.example.fah.FAHScreen.Account.SearchAccountActivity;
import com.example.fah.FAHScreen.Adapters.TestLayoutAdapter;
import com.example.fah.FAHScreen.Main.DetailSearchActivity;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.FAHScreen.Manage.ManageCategoryActivity;
import com.example.fah.FAHScreen.Manage.ManageTypePost;
import com.example.fah.FAHScreen.Models.TestLayout;
import com.example.fah.FAHScreen.Post.CreatePostActivity;
import com.example.fah.FAHScreen.Post.DetailPostActivity;
import com.example.fah.FAHScreen.Post.PostManagementActivity;
import com.example.fah.FAHScreen.User.PersionalImformationActivity;
import com.example.fah.FAHScreen.User.ProfileActivity;
import com.example.fah.R;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView lvLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        addControl();
//          addEvent();
    }

    private void addControl() {
            lvLayout=findViewById(R.id.lvActivity);
        ArrayList<TestLayout> layoutList = new ArrayList<TestLayout>();
        // add màn hình của mình ở đây để test
//        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"User Profile"));
//        layoutList.add(new TestLayout(HomeActivity.this, ProfileActivity.class,"Thông tin người dùng ThanhDC"));
//        layoutList.add(new TestLayout(HomeActivity.this, CreatePostActivity.class,"Tạo bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, PostManagementActivity.class,"Quản lý bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, DetailPostActivity.class,"Thông tin bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, DetailSearchActivity.class,"Tìm kiếm bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"Quản lý account"));
//        layoutList.add(new TestLayout(HomeActivity.this, MainActivity.class,"Main"));
        layoutList.add(new TestLayout(HomeActivity.this, MainActivity.class,"Main"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"Quản lý account"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByPostActivity.class,"Quản lý account theo bài viết"));
        layoutList.add(new TestLayout(HomeActivity.this, SearchAccountActivity.class,"Tìm kiếm account"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageCategoryActivity.class,"Quản lý danh mục"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageTypePost.class,"Loại bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, CreatePostActivity.class,"Tạo bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, DetailPostActivity.class,"Chi tiết bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, PostManagementActivity.class,"Quản lý bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, PersionalImformationActivity.class,"Profile user"));
//        layoutList.add(new TestLayout(HomeActivity.this, ProfileActivity.class,"Main"));



        TestLayoutAdapter layoutByAdminAdapter = new TestLayoutAdapter(
                   HomeActivity.this,
                    R.layout.layout_test_item,
                layoutList);
        lvLayout.setAdapter(layoutByAdminAdapter);
    }

}
