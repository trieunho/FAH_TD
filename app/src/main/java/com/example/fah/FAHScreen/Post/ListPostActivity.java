package com.example.fah.FAHScreen.Post;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;

public class ListPostActivity extends AppCompatActivity {

    Toolbar toolbar;
    Query myRef;
    ListView lstSearch;
    List<Post> listPost;

    // param
    int job;
    String location = "";
    int indexLocation;
    int salary;
    int dtFrom;
    int dtTo;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);

        // progress dialog
        progressDialog = new ProgressDialog(ListPostActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.setTitle("Waiting");

        addControls();
        addEvents();
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Danh sánh bài viết");
        setSupportActionBar(toolbar);

        // Get param
        Intent intent = getIntent();
        if (intent != null) {
            job = intent.getIntExtra("job", VALUEDEFAULT);
            location = intent.getStringExtra("location");
            indexLocation = intent.getIntExtra("indexLocation", VALUEDEFAULT);
            salary = intent.getIntExtra("salary", VALUEDEFAULT);
            dtFrom = intent.getIntExtra("dtFrom", VALUEDEFAULT);
            dtTo = intent.getIntExtra("dtTo", VALUEDEFAULT);
        }

        // Add controls
        lstSearch = findViewById(R.id.lstSearch);
        myRef = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, 1, FAHQueryParam.TypeInteger));
    }

    private void addEvents() {
        progressDialog.show();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPost = new ArrayList<>();
                List<Post> temp = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                if (temp == null) {
                    lstSearch.setAdapter(new SearchAdapter(ListPostActivity.this, new ArrayList<Post>()));
                    if (progressDialog.isShowing()) {
                        progressDialog.dismiss();
                    }
                    return;
                }
                Collections.sort(temp);

                for (Post item: temp) {
                    if (job != VALUEDEFAULT && !item.getCategory().getCategoryID().equals(String.valueOf(job + 1))) {
                        continue;
                    }

                    if (location != null && !location.equals("") && !item.getAddress().contains(location)) {
                        continue;
                    }

                    if (salary != VALUEDEFAULT) {
                        switch (salary) {
                            case 0:
                                if (item.getTypeOfSalary() != 2) {
                                    continue;
                                }
                                break;
                            case 1:
                                if (item.getTypeOfSalary() != 2 && (Integer.parseInt(item.getSalary_from()) < 1000000
                                        || Integer.parseInt(item.getSalary_from()) > 2000000)) {
                                    continue;
                                }
                                break;
                            case 2:
                                if (item.getTypeOfSalary() != 2 && (Integer.parseInt(item.getSalary_from()) < 2000000
                                        || Integer.parseInt(item.getSalary_from()) > 3000000)) {
                                    continue;
                                }
                                break;
                        }
                    }

                    if (dtFrom != VALUEDEFAULT && dtTo != VALUEDEFAULT) {
                        if (item.getDtFrom() < dtFrom || item.getDtTo() > dtTo) {
                            continue;
                        }
                    }

                    Post post = new Post(
                            item.getTitlePost(),
                            item.getCompanyName(),
                            item.getAddress(),
                            item.getDtFrom(),
                            item.getDtTo(),
                            item.getTypeOfSalary(),
                            item.getSalary_from(),
                            item.getSalary_to(),
                            item.getDeadLine()
                    );
                    post.setKey(item.getKey());
                    post.setKeyAccount(item.getKeyAccount());
                    listPost.add(post);
                }

                lstSearch.setAdapter(new SearchAdapter(ListPostActivity.this, listPost));
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        });

        lstSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListPostActivity.this, DetailPostActivity.class);
                intent.putExtra("key", listPost.get(position).getKey());
                intent.putExtra("keyAccount", listPost.get(position).getKeyAccount());
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
