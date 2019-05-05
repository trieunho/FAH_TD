package com.example.fah.FAHScreen.Post;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHControl.FAHCombobox;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHExcuteData.EmailValidator;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHModel.Models.TypeOfPost;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;
import static com.example.fah.FAHData.AccountData.userLogin;

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
    EditText dtFrom;
    EditText dtTo;
    EditText cbxTOP;
    EditText cbxField;

    FAHCombobox controlSalary;
    FAHCombobox controlType;
    FAHCombobox controlField;

    DatePickerDialog datePickerDialog;
    DatabaseReference myRef;

    int status = 0;
    Post dataUpdate;

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
                finish();
                return true;
            }
            case R.id.btnPost: {
                if (canPost() && CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                    TypeOfPost top = new TypeOfPost();
                    top.setTypeID(cbxTOP.getText().toString().substring(5, 6));

                    Category cgr = new Category();
                    cgr.setKey(String.valueOf(controlField.getItemChoose() + 1));
                    cgr.setCategoryID(String.valueOf(controlField.getItemChoose() + 1));
                    cgr.setCategoryName(cbxField.getText().toString());
                    if (getIntent().getStringExtra("key") == null) {
                        try {
                            dataUpdate = new Post(
                                    txtTitle.getText().toString(),
                                    txtCompanyName.getText().toString(),
                                    cgr,
                                    txtDescription.getText().toString(),
                                    txtRequired.getText().toString(),
                                    txtBenifit.getText().toString(),
                                    txtSoLuong.getText().toString(),
                                    txtAddress.getText().toString(),
                                    txtDate.getText().toString(),
                                    Integer.parseInt(dtFrom.getText().toString()),
                                    Integer.parseInt(dtTo.getText().toString()),
                                    controlSalary.getItemChoose(),
                                    txtLuong1.getText().toString(),
                                    txtLuong2.getText().toString(),
                                    txtEmail.getText().toString(),
                                    txtPhone.getText().toString(),
                                    top,
                                    userLogin.getKey(),
                                    new Date());
                            FAHQuery.InsertData(dataUpdate, "Post");

                            // update data Account: minus coin
                            myRef = FirebaseDatabase.getInstance().getReference("TYPE_OF_POST").child(top.getTypeID());
                            myRef.addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (userLogin == null) return;
                                    int coinNew = userLogin.getCoin() - Integer.parseInt(dataSnapshot.getValue(TypeOfPost.class).getTypeCoin());
                                    FAHQuery.UpdateData(coinNew, ExcuteString.GetUrlData("Account", userLogin.getKey(),"coin"));
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                }
                            });

                            Toast.makeText(this, "Tạo thành công", Toast.LENGTH_SHORT).show();

                        }
                        catch (Exception e) {
                            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        dataUpdate.setTitlePost(txtTitle.getText().toString());
                        dataUpdate.setCompanyName(txtCompanyName.getText().toString());
                        dataUpdate.setCategory(cgr);
                        dataUpdate.setJobDescription(txtDescription.getText().toString());
                        dataUpdate.setRequired(txtRequired.getText().toString());
                        dataUpdate.setBenifit(txtBenifit.getText().toString());
                        dataUpdate.setSoLuong(txtSoLuong.getText().toString());
                        dataUpdate.setAddress(txtAddress.getText().toString());
                        dataUpdate.setDeadLine(txtDate.getText().toString());
                        dataUpdate.setDtFrom(Integer.parseInt(dtFrom.getText().toString()));
                        dataUpdate.setDtTo(Integer.parseInt(dtTo.getText().toString()));
                        dataUpdate.setTypeOfSalary(controlSalary.getItemChoose());
                        dataUpdate.setSalary_from(txtLuong1.getText().toString());
                        dataUpdate.setSalary_to(txtLuong2.getText().toString());
                        dataUpdate.setEmail(txtEmail.getText().toString());
                        dataUpdate.setPhone(txtPhone.getText().toString());
                        FAHQuery.UpdateData(dataUpdate, "Post/" + getIntent().getStringExtra("key"));

                        Toast.makeText(this, "Cập nhật thành công", Toast.LENGTH_SHORT).show();
                        finish();
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
        int month = c.get(Calendar.MONTH) + 1;
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
        cbxTOP = findViewById(R.id.cbxTOP);
        cbxField = findViewById(R.id.cbxField);
        dtFrom = findViewById(R.id.dtFrom);
        dtTo = findViewById(R.id.dtTo);

        // combobox
        String[] arrLuong = {
                "Cố định",
                "Trong khoảng",
                "Thỏa thuận"
        };
        controlSalary = new FAHCombobox(CreatePostActivity.this, cbxLuong, arrLuong, 0);

        // combobox
        String[] arrTOP = {
                "Loại 1",
                "Loại 2",
                "Loại 3"
        };
        controlType = new FAHCombobox(CreatePostActivity.this, cbxTOP, arrTOP, 0);

        // Init
        txtLuong2.setVisibility(View.GONE);
        lbl.setVisibility(View.GONE);
        txvLoai.setText("Tiền không là tiền");
    }

    private void addEvents() {
		myRef = FAHQuery.GetData("CATEGORY_OF_POST");
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<Category> data = (List<Category>) FAHQuery.GetDataObject(dataSnapshot, new Category());
                String[] list = new String[data.size()];

                for (Category item: data) {
                    list[Integer.parseInt(item.getCategoryID()) - 1] = item.getCategoryName();
                }

                controlField = new FAHCombobox(CreatePostActivity.this, cbxField, list, VALUEDEFAULT);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(CreatePostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        txtDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    onClickDate(v);
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
                switch (controlSalary.getItemChoose()) {
                    case 0:
                        txtLuong1.setVisibility(View.VISIBLE);
                        txtLuong2.setVisibility(View.GONE);
                        txtLuong2.setText("");
                        lbl.setVisibility(View.GONE);
                        break;
                    case 1:
                        txtLuong1.setVisibility(View.VISIBLE);
                        txtLuong2.setVisibility(View.VISIBLE);
                        lbl.setVisibility(View.VISIBLE);
                        break;
                    case 2:
                        txtLuong1.setVisibility(View.GONE);
                        txtLuong2.setVisibility(View.GONE);
                        txtLuong1.setText("");
                        txtLuong2.setText("");
                        lbl.setVisibility(View.GONE);
                        break;
                }
            }
        });

        cbxTOP.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                switch (controlType.getItemChoose()) {
                    case 0:
                        txvLoai.setText("Tiền không là tiền");
                        break;
                    case 1:
                        txvLoai.setText("Tiền vừa đẹp");
                        break;
                    case 2:
                        txvLoai.setText("Tiền bố thí");
                        break;
                }
            }
        });

        initItem();
    }

    private void initItem(){
        if (getIntent().getStringExtra("key") != null && !getIntent().getStringExtra("key").isEmpty()) {
            FirebaseDatabase.getInstance().getReference().child("Post")
                    .child(getIntent().getStringExtra("key")).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        dataUpdate = dataSnapshot.getValue(Post.class);
                        txtTitle.setText(dataUpdate.getTitlePost());
                        txtCompanyName.setText(dataUpdate.getCompanyName());
                        controlField.setItemChoose(Integer.parseInt(dataUpdate.getCategory().getCategoryID()) - 1);
                        txtDescription.setText(dataUpdate.getJobDescription());
                        txtRequired.setText(dataUpdate.getRequired());
                        txtBenifit.setText(dataUpdate.getBenifit());
                        txtSoLuong.setText(dataUpdate.getSoLuong());
                        txtDate.setText(dataUpdate.getDeadLine());
                        txtAddress.setText(dataUpdate.getAddress());
                        dtFrom.setText(String.valueOf(dataUpdate.getDtFrom()));
                        dtTo.setText(String.valueOf(dataUpdate.getDtTo()));
                        controlSalary.setItemChoose(dataUpdate.getTypeOfSalary());
                        switch (controlSalary.getItemChoose()) {
                            case 0:
                                txtLuong1.setText(dataUpdate.getSalary_from());
                                break;
                            case 1:
                                txtLuong1.setText(dataUpdate.getSalary_from());
                                txtLuong2.setText(dataUpdate.getSalary_to());
                                break;
                            default:
                                break;
                        }
                        txtEmail.setText(dataUpdate.getEmail());
                        txtPhone.setText(dataUpdate.getPhone());
                        controlType.setItemChoose(Integer.parseInt(dataUpdate.getTypeOfPost().getTypeID()));
                        status = dataUpdate.getStatus();
                        cbxTOP.setEnabled(false);
                        txtTitle.requestFocus();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CreatePostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        }
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
            Toast.makeText(this, "Yêu cầu cơ bản không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtAddress.getText().toString().equals("")) {
            txtAddress.requestFocus();
            Toast.makeText(this, "Chưa điền địa chỉ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.parseInt(dtFrom.getText().toString()) < 0 || Integer.parseInt(dtFrom.getText().toString()) > 24){
            dtFrom.requestFocus();
            Toast.makeText(this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (Integer.parseInt(dtTo.getText().toString()) < 0 || Integer.parseInt(dtTo.getText().toString()) > 24){
            dtTo.requestFocus();
            Toast.makeText(this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (controlSalary.getItemChoose() == 0 && txtLuong1.getText().toString().equals("")) {
            txtLuong1.requestFocus();
            Toast.makeText(this, "Chưa nhập Lương", Toast.LENGTH_SHORT).show();
            return false;
        } else if (controlSalary.getItemChoose() == 1) {
            if (txtLuong1.getText().toString().equals("")) {
                txtLuong1.requestFocus();
                Toast.makeText(this, "Chưa nhập Lương trong khoảng", Toast.LENGTH_SHORT).show();
                return false;
            } else if (txtLuong2.getText().toString().equals("")){
                txtLuong2.requestFocus();
                Toast.makeText(this, "Chưa nhập Lương trong khoảng", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else if (txtEmail.getText().toString().equals("") || !EmailValidator.isValid(txtEmail.getText().toString())) {
            txtEmail.requestFocus();
            Toast.makeText(this, "Email không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (txtPhone.getText().toString().equals("")) {
            txtPhone.requestFocus();
            Toast.makeText(this, "Chưa nhập số điện thoại", Toast.LENGTH_SHORT).show();
            return false;
        } else if (cbxTOP.getText().toString().equals("")) {
            cbxTOP.requestFocus();
            Toast.makeText(this, "Chưa chọn mức phí cho bài viết", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }
}
