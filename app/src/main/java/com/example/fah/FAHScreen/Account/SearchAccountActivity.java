package com.example.fah.FAHScreen.Account;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Adapters.AccountBySearchAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHScreen.User.PersionalImformationActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SearchAccountActivity extends AppCompatActivity {
    Spinner spnListOfJob;
    ListView lvAccountSrch;
    TextView tvResultOfSearch;
    Button btnFind;

    ArrayList<Category> listCategory;
    ArrayList<Account> listAccount;
    static HashMap<Integer, String> spinnerMap;

    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_account_activity);
        addControl();
//        addEvent();
    }

    private void addEvent() {
        lvAccountSrch = findViewById(R.id.lvAccountSrch);
        lvAccountSrch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(SearchAccountActivity.this, AccountData.userLogin.getEmail(),
                        Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(SearchAccountActivity.this, PersionalImformationActivity.class);
                intent.putExtra("key", listAccount.get(position).getKey());
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControl() {

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("CATEGORY_OF_POST");

        spnListOfJob = findViewById(R.id.spnListOfJob);
        final EditText txtStartTime = findViewById(R.id.txtStartTime);
        final EditText txtEndTime = findViewById(R.id.txtEndTime);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listCategory = (ArrayList<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                setTitlePostAdapter(listCategory);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        lvAccountSrch = findViewById(R.id.lvAccountSrch);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        setListAdapter(listAccount);
        btnFind = findViewById(R.id.btnFind);
        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                spnListOfJob = findViewById(R.id.spnListOfJob);
                final String categoryID = spinnerMap.get(spnListOfJob.getSelectedItemPosition());
                final String startTime = String.valueOf(txtStartTime.getText());
                final String endTime = String.valueOf(txtEndTime.getText());

                if (validateInputTime(startTime) && validateInputTime(endTime)) {

                    myRef = database.getReference("Account");
                    myRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            listAccount = (ArrayList<Account>) FAHQuery.GetDataObject(dataSnapshot, new Account());
                            if (listAccount != null && listAccount.size() > 0) {
                                ArrayList<Account> listAcc = new ArrayList<>();

                                for (Account acc : listAccount) {
                                    if (acc.getRole() == 0) {
                                        if (acc.getCategory() != null
                                                && ("All".equals(categoryID)
                                                || categoryID.equals(acc.getCategory().getCategoryID()))) {


                                            if (startTime != null && !"".equals(startTime)) {
                                                int stTime = Integer.parseInt(startTime) - acc.getDtFrom();
                                                switch (stTime) {
                                                    case 1:
                                                        break;
                                                    case -1:
                                                        break;
                                                    case 0:
                                                        break;
                                                    case 23:
                                                        break;
                                                    case -23:
                                                        break;
                                                    default:
                                                        continue;
                                                }

                                            }

                                            if (endTime != null && !"".equals(endTime)) {
                                                int enTime = Integer.parseInt(endTime) - acc.getDtTo();
                                                switch (enTime) {
                                                    case 1:
                                                        break;
                                                    case -1:
                                                        break;
                                                    case 0:
                                                        break;
                                                    case 23:
                                                        break;
                                                    case -23:
                                                        break;
                                                    default:
                                                        continue;
                                                }
                                            }

                                            listAcc.add(acc);
                                        }
                                    }
                                }
                                setListAdapter(listAcc);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    private void setListAdapter(ArrayList<Account> listAccount) {
        if (listAccount != null && listAccount.size() > 0) {
            tvResultOfSearch.setText("Tìm thấy " + listAccount.size() + " kết quả");

            AccountBySearchAdapter accountBySearchAdapter = new AccountBySearchAdapter(
                    SearchAccountActivity.this,
                    R.layout.account_by_search_activity,
                    listAccount);
            lvAccountSrch.setAdapter(accountBySearchAdapter);
        } else {
            AccountBySearchAdapter accountBySearchAdapter = new AccountBySearchAdapter(
                    SearchAccountActivity.this,
                    R.layout.account_by_search_activity,
                    new ArrayList<Account>());
            lvAccountSrch.setAdapter(accountBySearchAdapter);
            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
        }
        addEvent();
    }

    /**
     * Set value for Adapter
     */
    private void setTitlePostAdapter(ArrayList<Category> categoryList) {

        List<String> listOfCategory = new ArrayList<>();
        spinnerMap = new HashMap<Integer, String>();
        spinnerMap.put(0, "All");
        listOfCategory.add("Tất cả");
        if (categoryList != null) {
            for (int i = 0; i < categoryList.size(); i++) {
                spinnerMap.put(i, categoryList.get(i).getCategoryID());
                listOfCategory.add(categoryList.get(i).getCategoryName());
            }

            ArrayAdapter<String> listOfPostAdapter =
                    new ArrayAdapter(
                            this, android.R.layout.simple_spinner_item,
                            listOfCategory);
            listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spnListOfJob.setAdapter(listOfPostAdapter);
        }
    }


    private boolean validateInputTime(String Time) {
        try {
            if (!"".equals(Time) || Time == null) {
                int startTime = Integer.parseInt(Time);
                if (startTime < 0 || startTime > 23) {
                    Toast.makeText(SearchAccountActivity.this, "Vui lòng nhập thời gian từ 0h-23h",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Toast.makeText(SearchAccountActivity.this, "Vui lòng nhập thời gian là số",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}