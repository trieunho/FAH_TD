package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Adapters.Sample_Firebase_Add_Edit_Delete_Account_Adapter;
import com.example.fah.FAHScreen.Models.SampleAccount;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Sample_Add_Edit_Delete_Account extends AppCompatActivity {
    ListView lvAccount;
    ArrayList<SampleAccount> accountList = new ArrayList<SampleAccount>();
    Button btnThem;
    Random rd = new Random();   // khai báo 1 đối tượng Random
    private int idrandom;
    Sample_Firebase_Add_Edit_Delete_Account_Adapter adapter;
    DatabaseReference myRef;
    FirebaseDatabase database;
    private static HashMap<String, SampleAccount> bullets = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sample__add__edit__delete__account);
       addControl();
        addEvent();
    }

    private void addControl() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Accounts");
        btnThem=findViewById(R.id.btnaddSample);
        lvAccount=findViewById(R.id.lvAccountSample);

        accountList.add(new SampleAccount("ThanhDC11", "fah032019@gmail.com", R.drawable.edit));
        adapter = new Sample_Firebase_Add_Edit_Delete_Account_Adapter(
                Sample_Add_Edit_Delete_Account.this,
                R.layout.rc_item_account,
                accountList
        );
        lvAccount.setAdapter(adapter);
    }

    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Sample_Add_Edit_Delete_Account.this, "Thêm", Toast.LENGTH_SHORT).show();
                idrandom = rd.nextInt();  // trả về 1 số nguyên bất kỳ
                 //để không trùng id con của root Account thì random số để không trùng.
                myRef.child("Account" + idrandom).setValue(new SampleAccount("ThanhDC11", "fah032019@gmail.com", R.drawable.avt));
                // Read from the database

            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                  //  GenericTypeIndicator<List<SampleAccount>> t = new GenericTypeIndicator<List<SampleAccount>>() {};
                  //  List<SampleAccount> messages = dataSnapshot.getValue(t);
                    //Toast.makeText(Sample_Add_Edit_Delete_Account.this, "Mọt line mới"+dataSnapshot.getValue().toString(), Toast.LENGTH_SHORT).show();
                   for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                     SampleAccount sampleAccount=snapshot.getValue(SampleAccount.class);
//                        bullets.put(snapshot.getKey(), sampleAccount);
                     //accountList2.add(new SampleAccount("sd","123",123));
                      Toast.makeText(Sample_Add_Edit_Delete_Account.this,sampleAccount.getName(), Toast.LENGTH_SHORT).show();
                  }
                  //  Toast.makeText(Sample_Add_Edit_Delete_Account.this, bullets.size(), Toast.LENGTH_SHORT).show();
                   // adapter.notifyDataSetChanged();
                    //lvAccount.setAdapter(adapter);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(Sample_Add_Edit_Delete_Account.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });


    }

}
