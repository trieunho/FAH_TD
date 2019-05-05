package com.example.fah.FAHScreen.Account;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHScreen.User.PersionalImformationActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

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
    Toolbar toolbar;

    AccountByAdminAdapter adapter;
    DatabaseReference myRef;
    Query query;
    FirebaseDatabase database;
    ArrayList <Account> accountList;
    Button btnSearch;
    String keySearch;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_account_by_admin);

        addControl();
        getListAccount();
        addEvent();
        eventChangedEditText();

    }

    /**
     * add control
     */
    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        btnSearch = findViewById(R.id.btnSearch);
        editTextSearch = findViewById(R.id.editTextSearch);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Account");

        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("Block người dùng");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    /**
     * Event click button search
     */
    private void addEvent() {

        //event click button search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                keySearch = editTextSearch.getText().toString();
                if (keySearch.isEmpty()) {
                    // Show notification invaid
                    Toast.makeText(ManageAccountByAdminActivity.this, "Bạn cần nhập tài khoản muốn tìm !", Toast.LENGTH_SHORT).show();
                } else {
                    search();
                }
            }
        });

        // Set event handle click item of list view Account
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageAccountByAdminActivity.this, PersionalImformationActivity.class);
                intent.putExtra("key", accountList.get(position).getKey());
                Toast.makeText(ManageAccountByAdminActivity.this, accountList.get(position).getKey(),
                        Toast.LENGTH_LONG).show();
                startActivity(intent);
            }
        });

     }

    /**
     * Set value for Adapter
     */
    private void setAccountAdapter(ArrayList <Account> accountList) {
        adapter = new AccountByAdminAdapter(
                ManageAccountByAdminActivity.this,
                R.layout.account_by_admin_activity,
                accountList);
        lvAccount.setAdapter(adapter);

        tvResultOfSearch.setText("Có tất cả " + accountList.size() + " tài khoản");
    }


    /**
     * Get list Account from DB
     */
    private void getListAccount() {
//        Query query = myRef.orderByChild()
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountList = (ArrayList<Account>) FAHQuery.GetDataObject(dataSnapshot, new Account());
                setAccountAdapter(accountList);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ManageAccountByAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /**
     * Event changed input search
     */
    private void eventChangedEditText() {
        editTextSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (editTextSearch.getText().length() == 0) {
                    getListAccount();
                } else {
                    search();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Exec input search
     */
    private void search() {
        "abc".contains("abc");
        keySearch = editTextSearch.getText().toString();
        query = myRef.orderByChild("accountName")
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

                    accountList.clear();
                    // set value listView
                    setAccountAdapter(accountList);
                    tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Failed to read value
                Toast.makeText(ManageAccountByAdminActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
