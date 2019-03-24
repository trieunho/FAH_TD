package com.example.fah.FAHScreen.Notification;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.fah.FAHScreen.Adapters.AccountAdapter;
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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account_activity);
        addControl();
//        addEvent();
    }

    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        ArrayList<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, R.drawable.men));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, R.drawable.men));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, R.drawable.men));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, R.drawable.men));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, R.drawable.men));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, R.drawable.men));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, R.drawable.men));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, R.drawable.men));
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, R.drawable.men));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, R.drawable.men));

        AccountAdapter accountAdapter = new AccountAdapter(
                ManageAccountActivity.this,
                R.layout.account_activity,
                accountList);
        lvAccount.setAdapter(accountAdapter);

    }

    private void addEvent() {

    }


}
