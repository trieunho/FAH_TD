package com.example.fah.TestControl;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import com.example.fah.FAHCommon.FAHMessage;
import com.example.fah.Main.HomeActivity;

import com.example.fah.R;

public class TestActivity extends AppCompatActivity {
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Kết quả tìm kiếm");
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_test, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(TestActivity.this, HomeActivity.class));

                finish();

                return true;
            }
            case R.id.action_1: {
                FAHMessage.ToastMessage(this, "Click button 1");
            }
            case R.id.action_2: {
                FAHMessage.ToastMessage(this, "Click button 2");
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
