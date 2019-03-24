package com.example.fah.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.fah.FAHScreen.Account.ManageAccountActivity;
import com.example.fah.FAHScreen.Adapters.TestLayoutAdapter;
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
        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountActivity.class,"Thông tin người dùng"));
        layoutList.add(new TestLayout(HomeActivity.this, ProfileActivity.class,"Thông tin người dùng ThanhDC"));
        layoutList.add(new TestLayout(HomeActivity.this, PersionalImformationActivity.class,"PersionalImformationActivity"));
        layoutList.add(new TestLayout(HomeActivity.this, CreatePostActivity.class,"CreatePostActivity"));
        layoutList.add(new TestLayout(HomeActivity.this, PostManagementActivity.class,"PostManagementActivity"));
        layoutList.add(new TestLayout(HomeActivity.this, DetailPostActivity.class,"DetailPostActivity"));




        TestLayoutAdapter layoutByAdminAdapter = new TestLayoutAdapter(
                   HomeActivity.this,
                    R.layout.layout_test_item,
                layoutList);
        lvLayout.setAdapter(layoutByAdminAdapter);
    }

}
