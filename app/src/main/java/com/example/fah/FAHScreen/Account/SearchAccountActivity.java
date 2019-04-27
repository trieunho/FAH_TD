package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Adapters.AccountBySearchAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.TypeOfPost;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SearchAccountActivity extends AppCompatActivity {
    Spinner spnListOfJob;
    Spinner spnListOfTime;
    ListView lvAccount;
    TextView tvResultOfSearch;
    ArrayList<TypeOfPost> listOfWork;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_account_activity);
        Toast.makeText(SearchAccountActivity.this, MainActivity.userLogin.getEmail(),
                Toast.LENGTH_SHORT).show();
        addControl();
//        addEvent();
    }

    private void addControl() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("TYPE_OF_WORK");

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

    private void getListTypeOfPost() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listOfWork = (ArrayList<TypeOfPost>) FAHQuery.GetDataObject(dataSnapshot, new TypeOfPost());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(SearchAccountActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}