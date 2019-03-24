package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.fah.FAHScreen.Adapters.AccountBySearchAdapter;
import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAccountActivity extends AppCompatActivity {
    Spinner spnListOfJob;
    Spinner spnListOfTime;
    ListView lvAccount;
    TextView tvResultOfSearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_account_activity);
        addControl();
//        addEvent();
    }

    private void addControl() {
        List<String> listOfWork = new ArrayList<>();
        listOfWork.add("Chọn công việc");
        listOfWork.add("Bảo vệ");
        listOfWork.add("Nhân viên bán hàng");
        listOfWork.add("Nhân viên tiếp thị");
        listOfWork.add("Nhân viên phục vụ");
        listOfWork.add("Việc phổ thông");
        listOfWork.add("Việc gia đình");

        List<String> listOfTime = new ArrayList<>();
        listOfTime.add("Chọn thời gian");
        listOfTime.add("Sáng");
        listOfTime.add("Chiều");
        listOfTime.add("Tối");
        listOfTime.add("Cả ngày");

        spnListOfJob = findViewById(R.id.spnListOfJob);
        spnListOfTime = findViewById(R.id.spnListOfTime);

        ArrayAdapter<String> adapterJob = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfWork);
        adapterJob.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListOfJob.setAdapter(adapterJob);

        ArrayAdapter<String> adapterTime = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfTime);
        adapterTime.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListOfTime.setAdapter(adapterTime);
//        spnListOfJob.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(SearchAccountActivity.this,
//                        spnListOfJob.getSelectedItem().toString(),
//                        Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });

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

            AccountBySearchAdapter accountBySearchAdapter = new AccountBySearchAdapter(
                    SearchAccountActivity.this,
                    R.layout.account_by_search_activity,
                    accountList);
            lvAccount.setAdapter(accountBySearchAdapter);
        } else {
            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
        }
    }
}