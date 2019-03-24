package com.example.fah.Main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.fah.FAHScreen.Main.Account.ManageAccountActivity;
import com.example.fah.FAHScreen.Main.Account.ManageAccountByPostActivity;
import com.example.fah.FAHScreen.Main.Account.SearchAccountActivity;
import com.example.fah.R;

public class HomeActivity extends AppCompatActivity {

        Button btnAdmin;
        Button btnCompany;
        Button btnSearchAccount;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.home_activity);
            addControl();
//        addEvent();
        }

    private void addControl() {
            btnAdmin = findViewById(R.id.btnAdmin);
            btnCompany = findViewById(R.id.btnCompany);
            btnSearchAccount = findViewById(R.id.btnSearchAccount);

            btnAdmin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ManageAccountActivity.class);
                    startActivity(intent);
                }
            });

            btnCompany.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, ManageAccountByPostActivity.class);
                    startActivity(intent);
                }
            });

            btnSearchAccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(HomeActivity.this, SearchAccountActivity.class);
                    startActivity(intent);
                }
            });
    }

}
