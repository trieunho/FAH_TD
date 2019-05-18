package com.example.fah.FAHScreen.Post;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

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
    EditText txtDtFrom;
    EditText txtDtTo;

    FAHCombobox controlJob;
    FAHCombobox controlLocation;
    FAHCombobox controlSalary;

    int job = VALUEDEFAULT;
    int location = VALUEDEFAULT;
    int salary = VALUEDEFAULT;
    String dtFrom;
    String dtTo;
    String fromScreen;

    DatabaseReference myRef;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_search_activity);

        // progress dialog
        progressDialog = new ProgressDialog(DetailSearchPostActivity.this);
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
                List<Category> data = (List<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                String[] list = new String[data.size() + 1];

                list[0] = "Tất cả";
                for (Category item : data) {
                    list[Integer.parseInt(item.getCategoryID())] = item.getCategoryName();
                }

                controlJob = new FAHCombobox(DetailSearchPostActivity.this, cbxJob, list, job);
                progressDialog.dismiss();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailSearchPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        // Get data from previous screen
        Intent intent = getIntent();
        if (intent != null) {
            fromScreen = intent.getStringExtra("fromScreen");
            job = intent.getIntExtra("job", VALUEDEFAULT);
            dtFrom = intent.getStringExtra("dtFrom");
            dtTo = intent.getStringExtra("dtTo");

            txtDtFrom.setText(String.valueOf(dtFrom));
            txtDtTo.setText(String.valueOf(dtTo));

        }
        if (fromScreen != null && "home".equals(fromScreen)) {
            if (canSearch() && CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                Intent intent1 = new Intent(DetailSearchPostActivity.this, ListPostActivity.class);
                intent1.putExtra("job", job);
                intent1.putExtra("location", String.valueOf(cbxCity.getText()).equals("Tất cả") ? "" : String.valueOf(cbxCity.getText()));
                intent1.putExtra("indexLocation", controlLocation.getItemChoose());
                intent1.putExtra("salary", controlSalary.getItemChoose());
                intent1.putExtra("dtFrom", String.valueOf(txtDtFrom.getText()).equals("") ? VALUEDEFAULT : Integer.parseInt(String.valueOf(txtDtFrom.getText())));
                intent1.putExtra("dtTo", String.valueOf(txtDtTo.getText()).equals("") ? VALUEDEFAULT : Integer.parseInt(String.valueOf(txtDtFrom.getText())));
                startActivity(intent1);
            }
        }
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
        txtDtFrom = findViewById(R.id.txtDtFrom);
        txtDtTo = findViewById(R.id.txtDtTo);

        myRef = FAHQuery.GetData("CATEGORY_OF_POST");

        controlLocation = new FAHCombobox(DetailSearchPostActivity.this, cbxCity, new String[]{
                "Tất cả",
                "An Giang",
                "Bà Rịa - Vũng Tàu",
                "Bắc Giang",
                "Bắc Kạn",
                "Bạc Liêu",
                "Bắc Ninh",
                "Bến Tre",
                "Bình Định",
                "Bình Dương",
                "Bình Phước",
                "Bình Thuận",
                "Cà Mau",
                "Cần Thơ",
                "Cao Bằng",
                "Đà Nẵng",
                "Đắk Lắk",
                "Đắk Nông",
                "Điện Biên",
                "Đồng Nai",
                "Đồng Tháp",
                "Gia Lai",
                "Hà Giang",
                "Hà Nam",
                "Hà Nội",
                "Hà Tĩnh",
                "Hải Dương",
                "Hải Phòng",
                "Hậu Giang",
                "Hòa Bình",
                "Hưng Yên",
                "Khánh Hòa",
                "Kiên Giang",
                "Kon Tum",
                "Lai Châu",
                "Lâm Đồng",
                "Lạng Sơn",
                "Lào Cai",
                "Long An",
                "Nam Định",
                "Nghệ An",
                "Ninh Bình",
                "Ninh Thuận",
                "Phú Thọ",
                "Phú Yên",
                "Quảng Bình",
                "Quảng Nam",
                "Quảng Ngãi",
                "Quảng Ninh",
                "Quảng Trị",
                "Sóc Trăng",
                "Sơn La",
                "Tây Ninh",
                "Thái Bình",
                "Thái Nguyên",
                "Thanh Hóa",
                "Thừa Thiên Huế",
                "Tiền Giang",
                "TP HCM",
                "Trà Vinh",
                "Tuyên Quang",
                "Vĩnh Long",
                "Vĩnh Phúc",
                "Yên Bái"
        }, location);

        controlSalary = new FAHCombobox(DetailSearchPostActivity.this, cbxSalary, new String[]{
                "Tất cả",
                "1.000.000 ~ 3.000.000",
                "3.000.000 ~ 5.000.000",
                "5 triệu trở lên"
        }, salary);


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
                if (canSearch() && CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                    Intent intent = new Intent(DetailSearchPostActivity.this, ListPostActivity.class);
                    intent.putExtra("job", controlJob != null ? controlJob.getItemChoose() : VALUEDEFAULT);
                    intent.putExtra("location", String.valueOf(cbxCity.getText()).equals("Tất cả") ? "" : String.valueOf(cbxCity.getText()));
                    intent.putExtra("indexLocation", controlLocation.getItemChoose());
                    intent.putExtra("salary", controlSalary.getItemChoose());
                    intent.putExtra("dtFrom", String.valueOf(txtDtFrom.getText()).equals("") ? VALUEDEFAULT : Integer.parseInt(String.valueOf(txtDtFrom.getText())));
                    intent.putExtra("dtTo", String.valueOf(txtDtTo.getText()).equals("") ? VALUEDEFAULT : Integer.parseInt(String.valueOf(txtDtFrom.getText())));
                    startActivity(intent);
                }

                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    private boolean canSearch() {

        if (!validateInputTime(String.valueOf(txtDtFrom.getText()))) {
            txtDtFrom.requestFocus();
            return false;
        } else if (!validateInputTime(String.valueOf(txtDtTo.getText()))) {
            txtDtTo.requestFocus();
            return false;
        } else {
            return true;
        }
    }

    private boolean validateInputTime(String Time) {
        try {
            if (!"".equals(Time) || Time == null) {
                int startTime = Integer.parseInt(Time);
                if (startTime < 0 || startTime > 23) {
                    Toast.makeText(DetailSearchPostActivity.this, "Vui lòng nhập thời gian từ 0h-23h",
                            Toast.LENGTH_LONG).show();
                    return false;
                }
            }
            return true;
        } catch (Exception e) {
            Toast.makeText(DetailSearchPostActivity.this, "Vui lòng nhập thời gian là số",
                    Toast.LENGTH_LONG).show();
            return false;
        }
    }
}
