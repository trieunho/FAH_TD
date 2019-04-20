package com.example.fah.FAHScreen.Post;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHExcuteData.EmailValidator;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.Main.HomeActivity;
import com.example.fah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class CreatePostActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText txtDate;
    EditText txtTitle;
    EditText txtDescription;
    EditText txtRequired;
    EditText txtAddress;
    EditText txtCompanyName;
    EditText txtBenifit;
    EditText txtSoLuong;
    EditText txtEmail;
    EditText txtPhone;
    EditText cbxLuong;
    EditText txtLuong1;
    TextView lbl;
    EditText txtLuong2;
    EditText txvLoai;
    String workingTime = "";
    EditText cbxTypeOfArticle;
    EditText cbxField;

    CheckBox ckb1;
    CheckBox ckb2;
    CheckBox ckb3;

    FAHCombobox cbx1;
    FAHCombobox cbx2;
    FAHCombobox cbx3;

    DatePickerDialog datePickerDialog;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        addControls();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.btn_post, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: {
                startActivity(new Intent(CreatePostActivity.this, HomeActivity.class));
                finish();
                return true;
            }
            case R.id.btnPost: {
                if (canPost() && CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                    try {
                        myRef.push().setValue(new Post(
                                txtTitle.getText().toString(),
                                txtCompanyName.getText().toString(),
                                cbxField.getText().toString(),
                                txtDescription.getText().toString(),
                                txtRequired.getText().toString(),
                                txtBenifit.getText().toString(),
                                txtSoLuong.getText().toString(),
                                txtAddress.getText().toString(),
                                txtDate.getText().toString(),
                                workingTime,
                                cbxLuong.getText().toString(),
                                txtLuong1.getText().toString(),
                                txtLuong2.getText().toString(),
                                txtEmail.getText().toString(),
                                txtPhone.getText().toString(),
                                Integer.parseInt(cbxTypeOfArticle.getText().toString().substring(4, 5))));
                    } catch (Exception e) {
                        Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
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
                        txtDate.requestFocus();
                    }
                }, year, month, day);
        datePickerDialog.show();
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Tạo bài viết");
        setSupportActionBar(toolbar);

        // add controls
        txtDate = findViewById(R.id.txtDate);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtRequired = findViewById(R.id.txtRequired);
        txtAddress = findViewById(R.id.txtAddress);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtBenifit = findViewById(R.id.txtBenifit);
        txtSoLuong = findViewById(R.id.txtSoLuong);
        txtEmail = findViewById(R.id.txtEmail);
        txtPhone = findViewById(R.id.txtPhone);
        cbxLuong = findViewById(R.id.cbxLuong);
        txtLuong1 = findViewById(R.id.txtLuong1);
        txtLuong2 = findViewById(R.id.txtLuong2);
        lbl = findViewById(R.id.lbl);
        txvLoai = findViewById(R.id.txvLoai);
        cbxTypeOfArticle = findViewById(R.id.cbxTypeOfArticle);
        cbxField = findViewById(R.id.cbxField);

        // workingTime
        ckb1 = findViewById(R.id.ckbTime1);
        ckb2 = findViewById(R.id.ckbTime2);
        ckb3 = findViewById(R.id.ckbTime3);

        // firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Post");

        // combobox
        String[] arrLuong = {
                "Cố định",
                "Trong khoảng",
                "Thỏa thuận"
        };
        cbx1 = new FAHCombobox(CreatePostActivity.this, cbxLuong, arrLuong, 0);

        String[] arrLoai = {
                "Loại 1",
                "Loại 2",
                "Loại 3"
        };
        cbx2 = new FAHCombobox(CreatePostActivity.this, cbxTypeOfArticle, arrLoai, 0);

        String[] arrField = {
                "Công nghệ thông tin",
                "Bất động sản",
                "Lĩnh vực giải trí"
        };
        cbx3 = new FAHCombobox(CreatePostActivity.this, cbxField, arrField, 0);
    }

    private boolean canPost(){
        if (txtTitle.getText().toString().equals("")) {
            txtTitle.requestFocus();
            Toast.makeText(this, "Tiêu đề không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtDescription.getText().toString().equals("")) {
            txtDescription.requestFocus();
            Toast.makeText(this, "Chưa mô tả công việc", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtRequired.getText().toString().equals("")) {
            txtRequired.requestFocus();
            Toast.makeText(this, "Basic Required không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtAddress.getText().toString().equals("")) {
            txtAddress.requestFocus();
            Toast.makeText(this, "Chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtEmail.getText().toString().equals("") || !EmailValidator.isValid(txtEmail.getText().toString())) {
            txtEmail.requestFocus();
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtPhone.getText().toString().equals("")) {
            txtPhone.requestFocus();
            Toast.makeText(this, "Chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        } else if (cbxTypeOfArticle.getText().toString().equals("")) {
            cbxTypeOfArticle.requestFocus();
            Toast.makeText(this, "Chưa chọn mức phí cho bài viết", Toast.LENGTH_SHORT).show();
            return false;
        }

        workingTime = ckb1.isChecked() ? "Morning" + (ckb2.isChecked() ? ", Afternoon" + (ckb3.isChecked() ? ", Evening" : "") : "" + (ckb3.isChecked() ? ", Evening" : "")) : "" + (ckb2.isChecked() ? "Afternoon" + (ckb3.isChecked() ? ", Evening" : "") : "" + (ckb3.isChecked() ? ", Evening" : ""));

        return true;
    }


}
