package com.example.fah.FAHScreen.Account;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Adapters.AccountByPostAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

/**
 * Manage Account By Post Activity
 * @createDate: 19/03/2019
 */
public class ManageAccountByPostActivity extends AppCompatActivity {

    ListView lvAccount;
    TextView tvResultOfSearch;
    Spinner spnListOfPost;
    Button btnSearch;
    Toolbar toolbar;

    ArrayList <Post> postList;
    ArrayList <Account> accountList;
    DatabaseReference myRef;
    FirebaseDatabase database;
    static HashMap<Integer,String> spinnerMap;

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
        myRef = database.getReference("Post");


        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("Danh sách ứng tuyển");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void addEvent() {

        //event click button search
        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search();
            }
        });
    }

    /**
     * Set value for Adapter
     */
    private void setTitlePostAdapter(ArrayList <Post> postList) {

        List <String> listOfPost = new ArrayList <>();
        spinnerMap = new HashMap <Integer, String>();

        if (postList != null) {
            for (int i = 0; i < postList.size(); i++) {
                spinnerMap.put(i, postList.get(i).getKey());
                listOfPost.add(postList.get(i).getTitlePost());
            }

            ArrayAdapter <String> listOfPostAdapter =
                    new ArrayAdapter(
                            this, android.R.layout.simple_spinner_item,
                            listOfPost);
            listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spnListOfPost.setAdapter(listOfPostAdapter);
        }
    }

    /**
     * Get list Post from DB
     */
    private void getListTittle() {
        if (AccountData.userLogin != null && AccountData.userLogin.getKey() != null) {
            myRef.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    postList = new ArrayList<>();
                    ArrayList<Post> listPostForAcc = (ArrayList<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                    for (Post item : listPostForAcc) {
                        if (item.getKeyAccount() != null && AccountData.userLogin.getKey().equals(item.getKeyAccount())) {
                            postList.add(item);
                        }
                    }

                    setTitlePostAdapter(postList);
                }

                @Override
                public void onCancelled(DatabaseError error) {
                    // Failed to read value
                    Toast.makeText(ManageAccountByPostActivity.this, "Error", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    /**
     * Get list acc when search
     */
    private void search() {
        Boolean checkFlag = false;

        // get key Post
        String keySearch = spinnerMap.get(spnListOfPost.getSelectedItemPosition());
        int size = 0;
        for (Post item : postList) {
            if (keySearch.equals(item.getKey())) {
                if (item.getListAccount() != null && item.getListAccount().size() > 0) {
                    for (int i = 0; i < item.getListAccount().size(); i++) {
                        FAHQuery.UpdateData(0, ExcuteString.GetUrlData("Post", item.getKey(), "listOfAccApply", String.valueOf(i), "statusSendInvation"));
//                        item.getListOfAccApply().get(i).setStatusSendInvation(0);
                    }
                }
                AccountByPostAdapter accountByPostAdapter = new AccountByPostAdapter(
                        this,
                        R.layout.account_by_post_activity,
                        item.getListOfAccApply());

                lvAccount.setAdapter(accountByPostAdapter);
                checkFlag = true;
                size = item.getListAccount().size();
                break;
            }
        }

        if (checkFlag) {
            tvResultOfSearch.setText("Tìm thấy " + size + " kết quả phù hợp");
        } else {
            tvResultOfSearch.setText("Không tìm thấy kết quả phù hợp !");
        }
    }

}
