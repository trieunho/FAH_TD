package com.example.fah.FAHScreen.Account;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHModel.Adapters.AccountByPostAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Post;
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
    String keySearch;
    ArrayList <Post> postList;
    ArrayList <Account> accountList;
    DatabaseReference myRef;
    Query query;
    FirebaseDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manage_account_by_post_activity);
        addControl();
        getListTittle();
        addEvent();
    }

    private void addControl() {
        lvAccount = findViewById(R.id.lvAccount);
        btnSearch = findViewById(R.id.btnSearch);
        tvResultOfSearch = findViewById(R.id.tvResultOfSearch);
        spnListOfPost = findViewById(R.id.spnListOfPost);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("PostTestHongCT");
//        myRef.child("Post1").setValue(new Post("1","b","c"));


//        List<String> listOfPost = new ArrayList<>();
//        listOfPost.add("Tittle bài viết 1");
//        listOfPost.add("Tittle bài viết 2");
//        listOfPost.add("Tittle bài viết 3");
//        listOfPost.add("Tittle bài viết 4");
//        listOfPost.add("Tittle bài viết 5");
//        listOfPost.add("Tittle bài viết 6");
//
//        ArrayAdapter<String> listOfPostAdapter =
//                new ArrayAdapter(
//                this, android.R.layout.simple_spinner_item,
//                listOfPost);
//        listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
//        spnListOfPost.setAdapter(listOfPostAdapter);
//
//        ArrayList<Account> accountList = new ArrayList<Account>();
//
//        if( accountList != null && accountList.size() > 0) {
//            tvResultOfSearch.setText("Tìm thấy " + accountList.size() + " kết quả");
//
//            AccountByPostAdapter accountByPostAdapter = new AccountByPostAdapter(
//                    this,
//                    R.layout.account_by_post_activity,
//                    accountList);
//            lvAccount.setAdapter(accountByPostAdapter);
//        } else {
//            tvResultOfSearch.setText("Không tìm thấy kết quả nào phù hợp !");
//        }

    }

    private void addEvent() {

        //event click button search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    search();
            }
        });

        // event click item in list
        lvAccount.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // showInputBox(accountList.get(position), position);
                showAlertDialog(accountList.get(position), position);
                Toast.makeText(ManageAccountByPostActivity.this, "You Clicked at ",
                        Toast.LENGTH_SHORT).show();
            }
        });

    }

    /**
     * Set value for Adapter
     */
    private void setPostAdapter(ArrayList <Post> postList) {


        List <String> listOfPost = new ArrayList <>();

        for (Post item : postList) {
            listOfPost.add(item.getTitlePost());
        }


        ArrayAdapter <String> listOfPostAdapter =
                new ArrayAdapter(
                        this, android.R.layout.simple_spinner_item,
                        listOfPost);
        listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListOfPost.setAdapter(listOfPostAdapter);
    }

    /**
     * Get list Post from DB
     */
    private void getListTittle() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                postList = new ArrayList <>();
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                if (dataSnapshot.getValue() != null) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
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

    /**
     * Set value for Adapter
     */
    private void setAccountAdapter(ArrayList <Account> accountList) {

            AccountByPostAdapter accountByPostAdapter = new AccountByPostAdapter(
                    this,
                    R.layout.account_by_post_activity,
                    accountList);

            lvAccount.setAdapter(accountByPostAdapter);
    }

    /**
     * Exec input search
     */
    private void search() {
        keySearch = spnListOfPost.getSelectedItem().toString();
        query = myRef.orderByChild("tittlePost").equalTo(keySearch);
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
                Toast.makeText(ManageAccountByPostActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
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

                Toast.makeText(ManageAccountByPostActivity.this, "Thay đổi trạng thái thành công", Toast.LENGTH_SHORT).show();

            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

    }

}
