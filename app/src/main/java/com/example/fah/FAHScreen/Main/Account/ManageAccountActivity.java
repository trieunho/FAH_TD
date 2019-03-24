package com.example.fah.FAHScreen.Main.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fah.FAHScreen.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.R;

import java.util.ArrayList;

/**
 * Manage Account Activity
 *
 * @author: NganTD1
 * @createDate: 19/03/2019
 */
public class ManageAccountActivity extends AppCompatActivity {

    ListView lvAccount;
    TextView tvResultOfSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account_activity);
        addControl();
//        addEvent();
    }

    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        ArrayList<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 0));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 0));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 0));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 0));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 0));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 0));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 0));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 0));

        if( accountList != null && accountList.size() > 0) {
            tvResultOfSearch.setText("Tìm thấy " + accountList.size() + " kết quả");

            AccountByAdminAdapter accountByAdminAdapter = new AccountByAdminAdapter(
                    ManageAccountActivity.this,
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
