package com.example.fah.FAHScreen.Post;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.CheckWifi;
import com.example.fah.FAHCommon.EmailValidator;
import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHExcuteData.Models.Post;
import com.example.fah.Main.HomeActivity;
import com.example.fah.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;
import java.util.Date;

public class CreatePostActivity extends AppCompatActivity {

    Toolbar toolbar;
    EditText txtDate;
    EditText txtTitle;
    EditText txtDescription;
    EditText txtRequired;
    EditText txtAddress;
    EditText txtCompanyName;
    EditText txtAboutCompany;
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

    CheckBox ckb1;
    CheckBox ckb2;
    CheckBox ckb3;

    FAHCombobox cbx1 = new FAHCombobox();;
    FAHCombobox cbx2 = new FAHCombobox();;

    DatePickerDialog datePickerDialog;
    DatabaseReference myRef;
    FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        addControls();
        addEvents();
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
                                txtAboutCompany.getText().toString(),
                                txtDescription.getText().toString(),
                                txtRequired.getText().toString(),
                                txtBenifit.getText().toString(),
                                txtSoLuong.getText().toString(),
                                txtAddress.getText().toString(),
                                txtDate.getText().toString().equals("") ? null : new Date(txtDate.getText().toString()),
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
        toolbar.setTitle("Create Post");
        setSupportActionBar(toolbar);

        // add controls
        txtDate = findViewById(R.id.txtDate);
        txtTitle = findViewById(R.id.txtTitle);
        txtDescription = findViewById(R.id.txtDescription);
        txtRequired = findViewById(R.id.txtRequired);
        txtAddress = findViewById(R.id.txtAddress);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtAboutCompany = findViewById(R.id.txtAboutCompany);
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

        // workingTime
        ckb1 = findViewById(R.id.ckbTime1);
        ckb2 = findViewById(R.id.ckbTime2);
        ckb3 = findViewById(R.id.ckbTime3);

        // Firebase
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Post");
    }

    private void addEvents() {
        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    onClickDate(v);
                }
            }
        });

        // Salary
        cbxLuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrLuong = {
                        "Fixed",
                        "From-To",
                        "Deal"
                };
                cbx1.ShowItemChoose(CreatePostActivity.this, cbxLuong, arrLuong);
            }
        });

        cbxLuong.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cbx1 = new FAHCombobox();
                    String[] arrLuong = {
                            "Fixed",
                            "From-To",
                            "Deal"
                    };
                    cbx1.ShowItemChoose(CreatePostActivity.this, cbxLuong, arrLuong);
                }
            }
        });
        cbxLuong.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (s.toString()) {
                    case "Fixed":
                        txtLuong1.setVisibility(View.VISIBLE);
                        txtLuong2.setVisibility(View.GONE);
                        txtLuong2.setText("");
                        lbl.setVisibility(View.GONE);
                        break;
                    case "From-To":
                        txtLuong1.setVisibility(View.VISIBLE);
                        txtLuong2.setVisibility(View.VISIBLE);
                        lbl.setVisibility(View.VISIBLE);
                        break;
                    case "Deal":
                        txtLuong1.setVisibility(View.GONE);
                        txtLuong2.setVisibility(View.GONE);
                        txtLuong1.setText("");
                        txtLuong2.setText("");
                        lbl.setVisibility(View.GONE);
                        break;
                }
            }
        });

        // Type of post
        cbxTypeOfArticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] arrLuong = {
                        "Type1",
                        "Type2",
                        "Type3"
                };
                cbx2.ShowItemChoose(CreatePostActivity.this, cbxTypeOfArticle, arrLuong);
            }
        });

        cbxTypeOfArticle.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    cbx2 = new FAHCombobox();
                    String[] arrLuong = {
                            "Type1",
                            "Type2",
                            "Type3"
                    };
                    cbx2.ShowItemChoose(CreatePostActivity.this, cbxTypeOfArticle, arrLuong);
                }
            }
        });
        cbxTypeOfArticle.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (s.toString()) {
                    case "Type1":
                        txvLoai.setText("Tiền không là tiền");
                        break;
                    case "Type2":
                        txvLoai.setText("Tiền vừa đẹp");
                        break;
                    case "Type3":
                        txvLoai.setText("Tiền bố thí");
                        break;
                }
            }
        });
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
