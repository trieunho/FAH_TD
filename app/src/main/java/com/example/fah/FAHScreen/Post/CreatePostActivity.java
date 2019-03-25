package com.example.fah.FAHScreen.Post;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import com.example.fah.R;
import java.util.Calendar;

public class CreatePostActivity extends AppCompatActivity {

    EditText txtDate;
    DatePickerDialog datePickerDialog;
    Spinner cbxLoai;
    Spinner cbxLuong;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        addControls();

        txtDate = findViewById(R.id.txtDate);
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    onClickDate(v);
                }
            }
        });
        cbxLuong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
//                switch (position) {
//                    case 0:
//                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void onClickDate(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        datePickerDialog = new DatePickerDialog(CreatePostActivity.this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        txtDate.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void addControls() {
        String[] arr = {
                "Loại 1",
                "Loại 2",
                "Loại 3"
        };

        cbxLoai = findViewById(R.id.cbxLoai);
        ArrayAdapter<String> adapterLoai = new ArrayAdapter<>(CreatePostActivity.this, android.R.layout.simple_spinner_item, arr);
        adapterLoai.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        cbxLoai.setAdapter(adapterLoai);

        String[] arrLuong = {
                "Cố định",
                "Trong khoảng",
                "Thỏa thuận"
        };

        cbxLuong = findViewById(R.id.cbxLuong);
        ArrayAdapter<String> adapterLuong = new ArrayAdapter<>(CreatePostActivity.this, android.R.layout.simple_spinner_item, arrLuong);
        adapterLuong.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        cbxLuong.setAdapter(adapterLuong);
    }
}
