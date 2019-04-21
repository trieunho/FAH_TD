package com.example.fah.FAHScreen.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Main.DetailSearchActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListPostActivity extends AppCompatActivity {

    Toolbar toolbar;
    DatabaseReference myRef;
    ListView lstSearch;

    // param
    String job;
    String location;
    int salary;
    String time1;
    String time2;
    String time3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);

        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Danh sánh bài viết");
        setSupportActionBar(toolbar);

        // Get param
        Intent intent = getIntent();
        job = intent.getStringExtra("job");
        location = intent.getStringExtra("location");
        salary = intent.getIntExtra("salary", -1);
        time1 = intent.getStringExtra("time1");
        time2 = intent.getStringExtra("time2");
        time3 = intent.getStringExtra("time3");

        // Add controls
        lstSearch = findViewById(R.id.lstSearch);

        myRef = FAHQuery.GetData("Post");

        addEvents();
    }

    private void addEvents() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> data = new ArrayList<>();
                List<Post> temp = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());

                for (Post item: temp) {
                    if (!job.equals("") && !job.equals(item.getField())) {
                        continue;
                    }

                    if (!location.equals("") && item.getAddress().indexOf(location) == -1) {
                        continue;
                    }

                    if (salary != -1) {
                        switch (salary) {
                            case 0:
                                if (!item.getTypeOfSalary().equals("Thỏa thuận")) {
                                    continue;
                                }
                                break;
                            case 1:
                                if (Integer.parseInt(item.getSalary_from()) < 1000000
                                        || Integer.parseInt(item.getSalary_from()) > 2000000) {
                                    continue;
                                }
                                break;
                            case 2:
                                if (Integer.parseInt(item.getSalary_from()) < 2000000
                                        && Integer.parseInt(item.getSalary_from()) > 3000000) {
                                    continue;
                                }
                                break;
                        }
                    }

                    Post post = new Post(
                            item.getTitlePost(),
                            item.getCompanyName(),
                            item.getAddress(),
                            item.getTimeOfWork(),
                            item.getTypeOfSalary().equals("Cố định") ? item.getSalary_from() :
                                    item.getTypeOfSalary().equals("Trong khoảng") ? item.getSalary_from() + " ~ " + item.getSalary_to() : "Thỏa thuận",
                            item.getDeadLine()
                    );
                    data.add(post);
                }

                lstSearch.setAdapter(new SearchAdapter(ListPostActivity.this, data));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(ListPostActivity.this, DetailSearchActivity.class));
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
