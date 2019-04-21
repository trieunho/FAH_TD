package com.example.fah.FAHScreen.Main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.*;
import android.support.v7.widget.Toolbar;

import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHScreen.Post.ListPostActivity;
import com.example.fah.Main.HomeActivity;
import com.example.fah.R;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;

public class DetailSearchActivity extends AppCompatActivity {

    Toolbar toolbar;
    Button btnSearch;

    EditText cbxJob;
    EditText cbxCity;
    EditText cbxSalary;

    FAHCombobox controlJob;
    FAHCombobox controlLocation;
    FAHCombobox controlSalary;

    CheckBox ckbTime1;
    CheckBox ckbTime2;
    CheckBox ckbTime3;

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_search_activity);

        addControls();
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

        ckbTime1 = findViewById(R.id.ckbTime1);
        ckbTime2 = findViewById(R.id.ckbTime2);
        ckbTime3 = findViewById(R.id.ckbTime3);

        String[] arrJob = {
                "Công nghệ thông tin",
                "Bất động sản",
                "Lĩnh vực giải trí"
        };
        controlJob = new FAHCombobox(DetailSearchActivity.this, cbxJob, arrJob, VALUEDEFAULT);

        controlLocation = new FAHCombobox(DetailSearchActivity.this, cbxCity, new String[] {
                "Đà Nẵng",
                "Hà Nội",
                "TP. Hồ Chí Minh"
        }, VALUEDEFAULT);

        controlSalary = new FAHCombobox(DetailSearchActivity.this, cbxSalary, new String[] {
                "Thỏa thuận",
                "1000000 ~ 2000000",
                "2000000 ~ 3000000"
        }, VALUEDEFAULT);
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
                startActivity(new Intent(DetailSearchActivity.this, HomeActivity.class));
                finish();
                return true;
            }
            case R.id.btnSearch: {
                Intent intent = new Intent(DetailSearchActivity.this, ListPostActivity.class);
                intent.putExtra("job", cbxJob.getText().toString());
                intent.putExtra("location", cbxCity.getText().toString());
                intent.putExtra("salary", controlSalary.getItemChoose());
                intent.putExtra("time1", ckbTime1.getText().toString());
                intent.putExtra("time2", ckbTime2.getText().toString());
                intent.putExtra("time3", ckbTime3.getText().toString());
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
