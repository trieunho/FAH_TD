package com.example.fah.FAHScreen.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHModel.Adapters.ListPostAdapter;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PostManagementActivity extends AppCompatActivity implements IOnButtonCLick {

    Toolbar toolbar;
    DatabaseReference myRef;
    Query approveQuery;
    CheckBox ckbApprove;
    CheckBox ckbUnApprove;
    ListView listView;
    ListPostAdapter listPostAdapter;
    List<Post> data = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_management_activity);

        addControls();
        addEvents();
    }

    private void addEvents() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                if (data == null) return;

                listPostAdapter.setData(data);
                listView.setAdapter(listPostAdapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PostManagementActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        ckbApprove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && ckbUnApprove.isChecked()) {
                    approveQuery = FAHQuery.GetData("Post");
                } else if (!isChecked && !ckbUnApprove.isChecked()) {
                    listView.setAdapter(new SearchAdapter(PostManagementActivity.this, null));
                    return;
                } else {
                    approveQuery = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, isChecked ? 1 : 0, FAHQueryParam.TypeInteger));
                }

                approveQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                        if (data == null) return;

                        listPostAdapter.setData(data);
                        listView.setAdapter(listPostAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(PostManagementActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        ckbUnApprove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && ckbApprove.isChecked()) {
                    approveQuery = FAHQuery.GetData("Post");
                } else if (!isChecked && !ckbApprove.isChecked()) {
                    listView.setAdapter(new SearchAdapter(PostManagementActivity.this, null));
                    return;
                } else {
                    approveQuery = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, isChecked ? 0 : 1, FAHQueryParam.TypeInteger));
                }

                approveQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                        if (data == null) return;

                        listPostAdapter.setData(data);
                        listView.setAdapter(listPostAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(PostManagementActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Tạo bài viết");
        setSupportActionBar(toolbar);

        listView = findViewById(R.id.lstPost);
        ckbApprove = findViewById(R.id.ckbApprove);
        ckbUnApprove = findViewById(R.id.ckbUnApprove);

        myRef = FAHQuery.GetData("Post");

        listPostAdapter = new ListPostAdapter(PostManagementActivity.this, data);
        listPostAdapter.registerBtnClick(PostManagementActivity.this);

        ckbApprove.setChecked(true);
        ckbUnApprove.setChecked(true);
    }

    @Override
    public void onBtnApproveClick(int position) {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            Post data = (Post) listView.getAdapter().getItem(position);
            data.setStatus(1);
            data.setApproveDate(new Date());
            FAHQuery.UpdateData(data, data.getClass().getSimpleName() + "/" + data.getKey());
        }
    }

    @Override
    public void onBtnDelClick(int position) {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            Post data = (Post) listView.getAdapter().getItem(position);
            FAHQuery.DeleteData(new String[]{ data.getClass().getSimpleName() + "/" + data.getKey() });
        }
    }

    @Override
    public void onItemClick(int position) {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            Intent intent = new Intent(PostManagementActivity.this, DetailPostActivity.class);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listPostAdapter.unRegisterBtnClick();
    }
}
