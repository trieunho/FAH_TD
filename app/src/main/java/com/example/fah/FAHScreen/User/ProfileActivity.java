package com.example.fah.FAHScreen.User;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;

public class ProfileActivity extends AppCompatActivity {
    TextView userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        addControl();
        addEvent();
    }

    private void addEvent() {
    }

    private void addControl() {
        userName=findViewById(R.id.userName);
        userName.setText(MainActivity.accountData.getUserLogin().getAccountName());
    }
}
