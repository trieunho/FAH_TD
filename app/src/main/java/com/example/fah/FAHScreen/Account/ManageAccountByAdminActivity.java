package com.example.fah.FAHScreen.Account;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHExcuteData.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHExcuteData.Models.Account;
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
        myRef = database.getReference("TestHongCT");


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
                    Toast.makeText(ManageAccountByAdminActivity.this, "Bạn cần tài khoản muốn tìm !", Toast.LENGTH_SHORT).show();
                } else {
                    search();
                }
            }
        });

        // event click item in list
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               // showInputBox(accountList.get(position), position);
                showAlertDialog(accountList.get(position), position);
                Toast.makeText(ManageAccountByAdminActivity.this, "You Clicked at ",
                        Toast.LENGTH_SHORT).show();
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
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountList = new ArrayList <>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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

    /**
     * Dialog defined
     */
    public void showInputBox(final Account account, final int index) {
        final Dialog dialog = new Dialog(ManageAccountByAdminActivity.this);
        dialog.setTitle("Chú ý !");
        dialog.setContentView(R.layout.update_category_item);
        TextView txtMessage = (TextView) dialog.findViewById(R.id.txtmessage);
        txtMessage.setText("Bạn có muốn thay đổi quyền của abc ?");
        txtMessage.setTextColor(Color.parseColor("#ff2222"));
        final EditText editText = (EditText) dialog.findViewById(R.id.txtinput);

        editText.setText(account.getAccountName());
        Button btnDongY = (Button) dialog.findViewById(R.id.btnDongY);
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                myRef.child(account.getAccountName()).child("statusBlock").setValue(1);

                Toast.makeText(ManageAccountByAdminActivity.this, "Thay đổi trạng thái thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        Button btnHuy = (Button) dialog.findViewById(R.id.btnHuy);
        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    /**
     * Extends showAlertDialog
     */
    public void showAlertDialog(final Account account, final int index){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chú ý 1");
        builder.setMessage("Bạn có muốn thay đổi quyền của abc ?");
        builder.setCancelable(false);
        builder.setPositiveButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        builder.setNegativeButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                myRef.child(account.getAccountName()).child("statusBlock").setValue(1);

                Toast.makeText(ManageAccountByAdminActivity.this, "Thay đổi trạng thái thành công", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }
}
