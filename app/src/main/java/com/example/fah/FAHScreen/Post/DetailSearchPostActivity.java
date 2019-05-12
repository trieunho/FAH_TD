package com.example.fah.FAHScreen.Post;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;

public class DetailSearchPostActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnSearch;

    EditText cbxJob;
    EditText cbxCity;
    EditText cbxSalary;
    EditText cbxTime;

    FAHCombobox controlJob;
    FAHCombobox controlLocation;
    FAHCombobox controlSalary;
    FAHCombobox controlTime;

    int job = VALUEDEFAULT;
    int location = VALUEDEFAULT;
    int salary = VALUEDEFAULT;
    int time = VALUEDEFAULT;

    DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_search_activity);

        addControls();
        addEvents();
    }

    private void addEvents() {
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Category> data = (List<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                String[] list = new String[data.size()];

                for (Category item: data) {
                    list[Integer.parseInt(item.getCategoryID()) - 1] = item.getCategoryName();
                }

                controlJob = new FAHCombobox(DetailSearchPostActivity.this, cbxJob, list, job);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailSearchPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Tìm kiếm bài viết");
        setSupportActionBar(toolbar);

        btnSearch = findViewById(R.id.btnSearch);
        cbxJob = findViewById(R.id.cbxJob);
        cbxCity = findViewById(R.id.cbxCity);
        cbxSalary = findViewById(R.id.cbxSalary);
        cbxTime = findViewById(R.id.cbxTime);

        myRef = FAHQuery.GetData("CATEGORY_OF_POST");

        controlLocation = new FAHCombobox(DetailSearchPostActivity.this, cbxCity, new String[] {
                "Đà Nẵng",
                "Hà Nội",
                "TP. Hồ Chí Minh"
        }, location);

        controlSalary = new FAHCombobox(DetailSearchPostActivity.this, cbxSalary, new String[] {
                "Thỏa thuận",
                "1000000 ~ 2000000",
                "2000000 ~ 3000000"
        }, salary);

        controlTime = new FAHCombobox(DetailSearchPostActivity.this, cbxTime, new String[] {
                "Từ 7 Giờ Đến 11 Giờ",
                "Từ 13 Giờ Đến 17 Giờ",
                "Từ 18 Giờ Đến 22 Giờ",
                "Từ 23 Giờ Đến 5 Giờ"
        }, time);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.btn_search, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                super.onBackPressed();
                return true;
            }
            case R.id.btnSearch: {
                if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                    Intent intent = new Intent(DetailSearchPostActivity.this, ListPostActivity.class);
                    intent.putExtra("job", controlJob != null ? controlJob.getItemChoose() : VALUEDEFAULT);
                    intent.putExtra("location", cbxCity.getText().toString());
                    intent.putExtra("indexLocation", controlLocation.getItemChoose());
                    intent.putExtra("salary", controlSalary.getItemChoose());
                    intent.putExtra("time", controlTime.getItemChoose());
                    startActivity(intent);
                }

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }
}
