package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fah.FAHExcuteData.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHExcuteData.Models.Account;
import com.example.fah.R;

import java.util.ArrayList;

/**
 * Manage Account Activity
 *
 * @author: NganTD1
 * @createDate: 19/03/2019
 */
public class ManageAccountByAdminActivity extends AppCompatActivity {

    ListView lvAccount;
    TextView tvResultOfSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account_by_admin);
        addControl();
//        addEvent();
    }

    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        ArrayList<Account> accountList = new ArrayList<Account>();

        if( accountList != null && accountList.size() > 0) {
            tvResultOfSearch.setText("Tìm thấy " + accountList.size() + " kết quả");

            AccountByAdminAdapter accountByAdminAdapter = new AccountByAdminAdapter(
                    ManageAccountByAdminActivity.this,
                    R.layout.account_by_admin_activity,
                    accountList);
            lvAccount.setAdapter(accountByAdminAdapter);
        } else {
            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
        }

    }

    private void addEvent() {

    }


}
