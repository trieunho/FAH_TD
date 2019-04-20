package com.example.fah.FAHScreen.Main;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Adapters.SearchAdapter;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    LinearLayout divSearch;
    ListView lstSearch;
    Button btnSearch;
    Button btnSearchDialog;
    Dialog searchDialog;

    EditText cbxJob;
    EditText cbxCity;
    EditText cbxSalary;
    EditText cbxLevel;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_search_activity);

        addControls();
        addEvents();
    }

    private void addEvents() {
        lstSearch.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(DetailSearchActivity.this, "OKE", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Tìm kiếm bài viết");
        setSupportActionBar(toolbar);

        divSearch = findViewById(R.id.divSearch);
        lstSearch = findViewById(R.id.lstSearch);
        btnSearch = findViewById(R.id.btnSearch);
        btnSearchDialog = findViewById(R.id.btnSearchDialog);
        cbxJob = findViewById(R.id.cbxJob);
        cbxCity = findViewById(R.id.cbxCity);
        cbxSalary = findViewById(R.id.cbxSalary);
        cbxLevel = findViewById(R.id.cbxLevel);

        myRef = FAHQuery.GetData("Post");
    }

    public void onClickSearch(View v) {
        searchDialog = new Dialog(DetailSearchActivity.this);
        searchDialog.setTitle("Search Dialog");
        searchDialog.setContentView(R.layout.search_dialog);
        searchDialog.show();
    }

    public void onClickSearchDialog(View v) {
        searchDialog.hide();

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> data = new ArrayList<>();
                List<Post> temp = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());

                for (Post item: temp) {
                    Post p = new Post(
                            item.getTitlePost(),
                            item.getCompanyName(),
                            item.getAddress(),
                            item.getWorkingTime(),
                            item.getTypeOfSalary().equals("Cố định") ? item.getSalary_from() :
                                    item.getTypeOfSalary().equals("Trong khoảng") ? item.getSalary_from() + " ~ " + item.getSalary_to() : "Thỏa thuận",
                            item.getDeadLine()
                    );
                    data.add(p);
                }

                lstSearch.setAdapter(new SearchAdapter(DetailSearchActivity.this, data));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailSearchActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
