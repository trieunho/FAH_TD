package com.example.fah.FAHScreen.Main.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fah.FAHScreen.Adapters.AccountByPostAdapter;
import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Manage Account By Post Activity
 *
 * @author: NganTD1
 * @createDate: 19/03/2019
 */
public class ManageAccountByPostActivity extends AppCompatActivity {

    ListView lvAccount;
    TextView tvResultOfSearch;
    Spinner spnListOfPost;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account_by_post_activity);
        addControl();
//        addEvent();
    }

    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        spnListOfPost = findViewById(R.id.spnListOfPost);

        List<String> listOfPost = new ArrayList<>();
        listOfPost.add("Tittle bài viết 1");
        listOfPost.add("Tittle bài viết 2");
        listOfPost.add("Tittle bài viết 3");
        listOfPost.add("Tittle bài viết 4");
        listOfPost.add("Tittle bài viết 5");
        listOfPost.add("Tittle bài viết 6");

        ArrayAdapter<String> listOfPostAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfPost);
        listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListOfPost.setAdapter(listOfPostAdapter);

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

            AccountByPostAdapter accountByPostAdapter = new AccountByPostAdapter(
                    this,
                    R.layout.account_by_post_activity,
                    accountList);
            lvAccount.setAdapter(accountByPostAdapter);
        } else {
            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
        }

    }

    private void addEvent() {

    }


}
