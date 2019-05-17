package com.example.fah.FAHScreen.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHControl.FAHMessage;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Adapters.AccountBySearchAdapter;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Account.ManageAccountByAdminActivity;
import com.example.fah.FAHScreen.User.PersionalImformationActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ManageCandidateByPost extends AppCompatActivity {

    Toolbar toolbar;
    EditText cbxPost;
    List<Post> dataPost;
    FAHCombobox controlTitle;
    ListView lstCandidate;
    int index = -1;
    ArrayList<Account> dataAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_candidate);

        addControls();

        if (AccountData.userLogin != null && AccountData.userLogin.getRole() == 2) {
            addEvents();
        }
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Danh sách ứng tuyển");
        setSupportActionBar(toolbar);

        cbxPost = findViewById(R.id.cbxPost);
        lstCandidate = findViewById(R.id.lstCandidate);
    }

    private void addEvents() {
        FAHQuery.GetDataQuery(
                new FAHQueryParam("Post", "keyAccount", FAHQueryParam.EQUAL,
                        AccountData.userLogin.getKey(), FAHQueryParam.TypeString))
                .addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataPost = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                if (dataPost == null || dataPost.size() == 0) return;
                String[] lstTitle = new String[dataPost.size()];

                for (int i = 0; i < dataPost.size(); i++) {
                    lstTitle[i] = dataPost.get(i).getTitlePost();
                    if (getIntent().getStringExtra("key") != null
                            && lstTitle[i].equals(getIntent().getStringExtra("key"))) {
                        index = i;
                    }
                }

                controlTitle = new FAHCombobox(ManageCandidateByPost.this, cbxPost, lstTitle, index);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                FAHMessage.ToastMessage(ManageCandidateByPost.this, databaseError.getMessage());
            }
        });

        cbxPost.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    dataAccount = new ArrayList<>();
                    FirebaseDatabase.getInstance().getReference("Account").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            List<Account> listAccount = (List<Account>) FAHQuery.GetDataObject(dataSnapshot, new Account());
                            if (listAccount != null && listAccount.size() > 0) {
                                List<String> keyAccount = dataPost.get(controlTitle.getItemChoose()).getListAccount();
                                if (keyAccount != null && keyAccount.size() > 0) {
                                    for (int i = 0; i < keyAccount.size(); i++) {
                                        for (int j = 0; j < listAccount.size(); j++) {
                                            if (keyAccount.get(i).equals(listAccount.get(j).getKey())) {
                                                dataAccount.add(listAccount.get(j));
                                                break;
                                            }
                                        }
                                    }

                                    if (dataAccount.size() > 0) {
                                        lstCandidate.setAdapter(new AccountBySearchAdapter(ManageCandidateByPost.this, 10, dataAccount));
                                    }
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }
        });

        lstCandidate.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ManageCandidateByPost.this, PersionalImformationActivity.class);
                intent.putExtra("key", dataAccount.get(position).getKey());
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
