package com.example.fah.FAHScreen.Main;

import android.app.Dialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
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
    EditText cbxTime;

    FAHCombobox controlJob;
    FAHCombobox controlLocation;
    FAHCombobox controlSalary;
    FAHCombobox controlTime;

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
        cbxTime = findViewById(R.id.cbbTime);

        myRef = FAHQuery.GetData("Post");

        String[] arrJob = {
                "Công nghệ thông tin",
                "Bất động sản",
                "Lĩnh vực giải trí"
        };
        controlJob = new FAHCombobox(this, cbxJob, arrJob, -1);

        controlLocation = new FAHCombobox(DetailSearchActivity.this, cbxCity, new String[] {
                "Đà Nẵng",
                "Hà Nội",
                "TP. Hồ Chí Minh"
        }, 0);

        controlSalary = new FAHCombobox(DetailSearchActivity.this, cbxSalary, new String[] {
                "Thỏa thuận",
                "1000000 ~ 2000000",
                "2000000 ~ 3000000"
        }, 0);

        controlTime = new FAHCombobox(DetailSearchActivity.this, cbxTime, new String[] {
                "Buổi sáng",
                "Buổi chiều",
                "Buổi tối"
        }, 0);
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
                    if (!cbxJob.getText().toString().equals("") && item.getField().equals(cbxJob.getText().toString())) {
                        Post p = new Post(
                                item.getTitlePost(),
                                item.getCompanyName(),
                                item.getAddress(),
                                item.getTimeOfWork(),
                                item.getTypeOfSalary().equals("Cố định") ? item.getSalary_from() :
                                        item.getTypeOfSalary().equals("Trong khoảng") ? item.getSalary_from() + " ~ " + item.getSalary_to() : "Thỏa thuận",
                                item.getDeadLine()
                        );
                        data.add(p);
                    } else {

                    }
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
