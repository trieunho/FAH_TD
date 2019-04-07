package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    EditText editTextSearch;
    AccountByAdminAdapter adapter;
    DatabaseReference myRef;
    Query query;
    FirebaseDatabase database;
    ArrayList<Account> accountList;
    Button btnSearch;
    String keySearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account_by_admin);

        addControl();
        getListAccount();
        addEvent();
    }


    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        btnSearch = findViewById(R.id.btnSearch);
        editTextSearch = findViewById(R.id.editTextSearch);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Account");

//        ArrayList<Account> accountList = new ArrayList<Account>();
//        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
//        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));
//        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));

//        if( accountList != null && accountList.size() > 0) {
//            tvResultOfSearch.setText("Tìm thấy " + accountList.size() + " kết quả");
//
//            AccountByAdminAdapter accountByAdminAdapter = new AccountByAdminAdapter(
//                    ManageAccountByAdminActivity.this,
//                    R.layout.account_by_admin_activity,
//                    accountList);
//            lvAccount.setAdapter(accountByAdminAdapter);
//        } else {
//            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
//        }

    }

    private void addEvent() {

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keySearch = editTextSearch.getText().toString();
                query = myRef.orderByChild("name")
                        .startAt(keySearch)
                        .endAt(keySearch + "\uf8ff");
                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        accountList = new ArrayList <>();
                        // This method is called once with the initial value and again
                        // whenever data at this location is updated.
                        if (dataSnapshot.getValue() != null) {

                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                Account account = snapshot.getValue(Account.class);
                                accountList.add(account);
                            }

                            // set value listView
                            setAccountAdapter(accountList);
                            tvResultOfSearch.setText("Tìm thấy " + accountList.size() + " kết quả phù hợp");
                        } else {
                            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
                            accountList.clear();
                            // set value listView
                            setAccountAdapter(accountList);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        // Failed to read value
                        Toast.makeText(ManageAccountByAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }

    /**
     * Set value for Adapter
     */
    private void setAccountAdapter(ArrayList<Account> accountList) {
        adapter = new AccountByAdminAdapter(
                ManageAccountByAdminActivity.this,
                R.layout.account_by_admin_activity,
                accountList);
        lvAccount.setAdapter(adapter);
    }



    /**
     * Get list Account from DB
     */
    private void getListAccount() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountList = new ArrayList<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Account account = snapshot.getValue(Account.class);
                        accountList.add(account);
                    }

                    setAccountAdapter(accountList);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ManageAccountByAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
