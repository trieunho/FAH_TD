package com.example.fah.FAHScreen.Account;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Adapters.AccountByAdminAdapter;
import com.example.fah.FAHScreen.Adapters.AccountByPostAdapter;
import com.example.fah.FAHScreen.Models.Account;
import com.example.fah.FAHScreen.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

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
    Button btnSearch;

    DatabaseReference myRef;
    FirebaseDatabase database;

    Query query;
    ArrayAdapter<String> listOfPostAdapter;
    AccountByAdminAdapter adapter;

    ArrayList<Post> postList;
    List<String> listOfPost;
    ArrayList<Account> accountList;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account_by_post_activity);
        addControl();
        getListPost();
//        addEvent();
    }

    private void addControl() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Post");

        btnSearch = findViewById(R.id.btnSearch);
        lvAccount = findViewById(R.id.lvAccount);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        spnListOfPost = findViewById(R.id.spnListOfPost);

      //  List<String> listOfPost = new ArrayList<>();
//        listOfPost.add("Tittle bài viết 1");
//        listOfPost.add("Tittle bài viết 2");


//        listOfPostAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfPost);
//        listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        spnListOfPost.setAdapter(listOfPostAdapter);

        ArrayList<Account> accountList = new ArrayList<Account>();
        accountList.add(new Account("Cao Thị Hồng","caohong96@gmail.com",0, 1));
        accountList.add(new Account("Trần Duy Ngân","tranduyngan0000@gmail.com", 1, 1));

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

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef = database.getReference("Account");
                query = myRef.orderByChild("titlePost").equalTo("titlePost_click");

                query.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
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
                        Toast.makeText(ManageAccountByPostActivity.this, "Error", Toast.LENGTH_SHORT).show();

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
                ManageAccountByPostActivity.this,
                R.layout.account_by_admin_activity,
                accountList);
        lvAccount.setAdapter(adapter);
    }

    private void setPostAdapter(ArrayList<Post> postList) {
            for (Post item : postList) {
              String titlePost =  item.getTitlePost();
                listOfPost.add(titlePost);
            }

        listOfPostAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, listOfPost);
        listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListOfPost.setAdapter(listOfPostAdapter);
    }

    private void getListPost() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList = new ArrayList<>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot: dataSnapshot.getChildren()) {
                        Post post = snapshot.getValue(Post.class);
                        postList.add(post);
                    }

                   setPostAdapter(postList);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(ManageAccountByPostActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
