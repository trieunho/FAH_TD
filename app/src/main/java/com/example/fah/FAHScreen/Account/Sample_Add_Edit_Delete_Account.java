package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.TestDB;
import com.example.fah.FAHExcuteData.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        myRef = FAHQuery.GetData("Post");
//        query = myRef.orderByChild("name").startAt("\uf8ff3\uf8ff");
////        FAHQueryParam queryParam = new FAHQueryParam("Account", "email", FAHQueryParam.EQUAL, "123456789", FAHQueryParam.TypeString);
////        query = FAHQuery.GetDataQuery(queryParam);
    }

    private void addEvent() {
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TestDB data = new TestDB("123","123","123","123","123",new Date());
                FAHQuery.InsertData(data);
//                boolean test = "123456".toLowerCase().contains("34".toLowerCase());
//                FAHMessage.ToastMessage(Sample_Add_Edit_Delete_Account.this, "123");
            }
        });

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
//                accountList = (ArrayList<TestDB>) FAHQuery.GetDataObject(dataSnapshot, new TestDB());
                List<Post> postList = (ArrayList<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(Sample_Add_Edit_Delete_Account.this, "Lỗi", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
