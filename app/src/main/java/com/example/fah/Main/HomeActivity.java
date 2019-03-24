package com.example.fah.Main;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.fah.FAHScreen.Adapters.TestLayoutAdapter;
import com.example.fah.FAHScreen.Main.Account.ManageAccountActivity;
import com.example.fah.FAHScreen.Models.TestLayout;
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



        TestLayoutAdapter layoutByAdminAdapter = new TestLayoutAdapter(
                   HomeActivity.this,
                    R.layout.layout_test_item,
                layoutList);
        lvLayout.setAdapter(layoutByAdminAdapter);
    }

}
