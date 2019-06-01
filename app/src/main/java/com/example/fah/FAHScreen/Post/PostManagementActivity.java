package com.example.fah.FAHScreen.Post;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Adapters.ListPostAdapter;
import com.example.fah.FAHModel.Models.Notification;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHMessage.AlertDialogMessage;

public class PostManagementActivity extends AppCompatActivity implements IOnButtonCLick, IConfirmClick {

    Toolbar toolbar;
    DatabaseReference myRef;
    Query approveQuery;
    CheckBox ckbApprove;
    CheckBox ckbUnApprove;
    ListView listView;
    ListPostAdapter listPostAdapter;
    List<Post> data = new ArrayList<>();
    int position;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_management_activity);

        // progress dialog
        progressDialog = new ProgressDialog(PostManagementActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.setTitle("Waiting");

        addControls();
        addEvents();
    }

    private void addEvents() {
        progressDialog.show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                if (data == null) {
                    listPostAdapter.setData(new ArrayList<Post>());
                    listView.setAdapter(listPostAdapter);
                    return;
                }

                listPostAdapter.setData(data);
                listView.setAdapter(listPostAdapter);
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(PostManagementActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

        ckbApprove.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked && ckbUnApprove.isChecked()) {
                    approveQuery = FAHQuery.GetData("Post");
                } else if (!isChecked && !ckbUnApprove.isChecked()) {
                    listPostAdapter.setData(new ArrayList<Post>());
                    listView.setAdapter(listPostAdapter);
                    return;
                } else {
                    approveQuery = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, isChecked ? 1 : 0, FAHQueryParam.TypeInteger));
                }

                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
                approveQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                        if (data == null) {
                            listPostAdapter.setData(new ArrayList<Post>());
                            listView.setAdapter(listPostAdapter);
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            return;
                        }

                        listPostAdapter.setData(data);
                        listView.setAdapter(listPostAdapter);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(PostManagementActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
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
                    listPostAdapter.setData(new ArrayList<Post>());
                    listView.setAdapter(listPostAdapter);
                    return;
                } else {
                    approveQuery = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, isChecked ? 0 : 1, FAHQueryParam.TypeInteger));
                }

                if (!progressDialog.isShowing()) {
                    progressDialog.show();
                }
                approveQuery.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                        if (data == null) {
                            listPostAdapter.setData(new ArrayList<Post>());
                            listView.setAdapter(listPostAdapter);
                            if (progressDialog.isShowing()) {
                                progressDialog.dismiss();
                            }
                            return;
                        }

                        listPostAdapter.setData(data);
                        listView.setAdapter(listPostAdapter);

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
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
        toolbar.setTitle("Duyệt bài viết");
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
            FirebaseDatabase.getInstance().getReference(data.getClass().getSimpleName() + "/" + data.getKey()).setValue(data);

            ArrayList<Notification> notice = new ArrayList<>();
            for (int i = 0; i < 2; i++) {
                Notification item = new Notification();
                item.setKeyPost(data.getKey());
                if (i == 0) {
                    item.setAccountKey(AccountData.userLogin.getKey());
                    item.setTitle("Đã duyệt bài viết");
                }
                else {
                    item.setAccountKey(data.getKeyAccount());
                    item.setTitle("Bài viết đã được duyệt bởi admin");
                }

                notice.add(item);
            }

            FAHQuery.InsertData(notice, "Notification");
        }
    }

    @Override
    public void onBtnDelClick(int position) {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            this.position = position;
            AlertDialogMessage(PostManagementActivity.this, "Xác nhận", "Bạn có thực sự muốn xóa ?", "Có", "Không");
        }
    }

    @Override
    public void onItemClick(int position, String accountName, String key) {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            Intent intent = new Intent(PostManagementActivity.this, DetailPostActivity.class);
            intent.putExtra("key", key);
            intent.putExtra("position", position);
            intent.putExtra("pic", accountName);
            startActivity(intent);
        }
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        listPostAdapter.unRegisterBtnClick();
    }

    @Override
    public void onYesClick() {
        Post data = (Post) listView.getAdapter().getItem(position);
        FAHQuery.DeleteData(new String[]{ data.getClass().getSimpleName() + "/" + data.getKey() });
    }

    @Override
    public void onOkClick() {

    }
}
