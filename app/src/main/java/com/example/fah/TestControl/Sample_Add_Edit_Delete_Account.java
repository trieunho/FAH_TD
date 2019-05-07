package com.example.fah.TestControl;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.TestDB;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Sample_Add_Edit_Delete_Account extends AppCompatActivity {
    Button btnThem;
    ListView lvAccount;
    ArrayList<TestDB> accountList = new ArrayList<>();
    DatabaseReference myRef;
    Query query;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample__add__edit__delete__account);
        addControl();
        addEvent();
    }

    private void addControl() {
        btnThem=findViewById(R.id.btnaddSample);
        lvAccount=findViewById(R.id.lvAccountSample);

        myRef = FAHQuery.GetData("TestDB");
    }

    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDB test = accountList.get(0);
                test.setTitlePost("Không được nghe");
                FAHQuery.UpdateData(test);
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                accountList = (ArrayList<TestDB>) FAHQuery.GetDataObject(dataSnapshot, new TestDB());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(Sample_Add_Edit_Delete_Account.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
