package com.example.fah.FAHScreen.Main;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHDatabase.Table.FAHQueryParam;
import com.example.fah.FAHExcuteData.Adapters.SearchAdapter;
import com.example.fah.FAHExcuteData.Models.Post;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
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

        List<Post> data = getListData();
        final ListView listView = findViewById(R.id.lstSearch);
        listView.setAdapter(new SearchAdapter(DetailSearchActivity.this, data));
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

        lstSearch.setVisibility(View.GONE);
    }

    public void onClickSearch(View v) {
        searchDialog = new Dialog(DetailSearchActivity.this);
        searchDialog.setTitle("Search Dialog");
        searchDialog.setContentView(R.layout.search_dialog);
        searchDialog.show();
    }

    public void onClickSearchDialog(View v) {
        searchDialog.hide();
        final Post param = new Post();
        param.setAddress("abc");
        Query a = FAHQuery.GetDataQuery(new FAHQueryParam("Post", "email", "EQUAL", param, FAHQueryParam.TypeString));
        a.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Post> data = (List<Post>) FAHQuery.GetDataObject(dataSnapshot, new Post());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private List<Post> getListData() {
        List<Post> list = new ArrayList<>();
        Post vietnam = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
        Post usa = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));
        Post russia = new Post("Tuyển nhân viên phục vụ cà phê", "QUÁN CÀ PHÊ VEN ĐƯỜNG", "Địa điểm", "Thời gian làm việc", "Mức lương", new Date("01/04/2019"));

        list.add(vietnam);
        list.add(usa);
        list.add(russia);

        return list;
    }
}
