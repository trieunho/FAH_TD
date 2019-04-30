package com.example.fah.FAHScreen.Post;

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
    List<Post> data;

    // param
    int job;
    String location = "";
    int indexLocation;
    int salary;
    int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_post);

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
        job = intent.getIntExtra("job", VALUEDEFAULT);
        location = intent.getStringExtra("location");
        indexLocation = intent.getIntExtra("indexLocation", VALUEDEFAULT);
        salary = intent.getIntExtra("salary", VALUEDEFAULT);
        time = intent.getIntExtra("time", VALUEDEFAULT);

        // Add controls
        lstSearch = findViewById(R.id.lstSearch);
        myRef = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "status", FAHQueryParam.EQUAL, 1, FAHQueryParam.TypeInteger));
    }

    private void addEvents() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = new ArrayList<>();
                List<Post> temp = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
                if (temp == null) return;
                Collections.sort(temp);

                for (Post item: temp) {
                    if (job != VALUEDEFAULT && !item.getCategory().getCategoryID().equals(String.valueOf(job + 1))) {
                        continue;
                    }

                    if (!location.equals("") && item.getAddress().contains(location)) {
                        continue;
                    }

                    if (salary != VALUEDEFAULT) {
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

                    if (time != VALUEDEFAULT) {
                        switch (time) {
                            case 0:
                                if (item.getDtFrom() <= 8 && item.getDtFrom() >= 6
                                        && item.getDtTo() <= 12 && item.getDtTo() >= 10){
                                    break;
                                } else {
                                    continue;
                                }
                            case 1:
                                if (item.getDtFrom() <= 14 && item.getDtFrom() >= 12
                                        && item.getDtTo() <= 18 && item.getDtTo() >= 16){
                                    break;
                                } else {
                                    continue;
                                }
                            case 2:
                                if (item.getDtFrom() <= 19 && item.getDtFrom() >= 17
                                        && item.getDtTo() <= 23 && item.getDtTo() >= 21){
                                    break;
                                } else {
                                    continue;
                                }
                            case 3:
                                if ((item.getDtFrom() == 22 || item.getDtFrom() == 23 || item.getDtFrom() == 24 || item.getDtFrom() == 0)
                                        && item.getDtTo() >= 4 && item.getDtTo() <= 6) {
                                    break;
                                } else {
                                    continue;
                                }
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
                    data.add(post);
                }

                lstSearch.setAdapter(new SearchAdapter(ListPostActivity.this, data));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        lstSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ListPostActivity.this, DetailPostActivity.class);
                intent.putExtra("key", data.get(position).getKey());
                intent.putExtra("job", job);
                intent.putExtra("location", location);
                intent.putExtra("indexLocation", indexLocation);
                intent.putExtra("salary", salary);
                intent.putExtra("time", time);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                Intent intent = new Intent(ListPostActivity.this, DetailSearchPostActivity.class);
                intent.putExtra("job", job);
                intent.putExtra("location", location);
                intent.putExtra("salary", salary);
                intent.putExtra("time", time);
                startActivity(intent);
                finish();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
