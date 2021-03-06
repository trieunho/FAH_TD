package com.example.fah.FAHScreen.Post;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
import com.example.fah.FAHCommon.FAHControl.FAHMessage;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHCommon.FAHExcuteData.EmailValidator;
import com.example.fah.FAHCommon.FAHExcuteData.ExcuteString;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.Notification;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHModel.Models.TypeOfPost;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.example.fah.FAHCommon.FAHControl.FAHCombobox.VALUEDEFAULT;
import static com.example.fah.FAHCommon.FAHControl.FAHMessage.AlertDialogMessage;
import static com.example.fah.FAHCommon.FAHControl.FAHMessage.ImformationDialogMessage;
import static com.example.fah.FAHCommon.FAHControl.FAHMessage.ToastMessage;
import static com.example.fah.FAHData.AccountData.userLogin;

public class CreatePostActivity extends AppCompatActivity implements IConfirmClick {

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
    EditText dtFrom;
    EditText dtTo;
    EditText cbxTOP;
    EditText cbxField;
    TextView txtContentNote;

    FAHCombobox controlSalary;
    FAHCombobox controlType;
    FAHCombobox controlField;

    DatePickerDialog datePickerDialog;
    DatabaseReference myRef;

    int status = 0;
    Post dataUpdate;
    List<TypeOfPost> lstTOP;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_post);

        // progress dialog
        progressDialog = new ProgressDialog(CreatePostActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Đang tải dữ liệu...");
        progressDialog.setTitle("Waiting");

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
                String message = "";
                // Tạo bài viết mới
                if (getIntent().getStringExtra("key") == null) {
                    if (canPost() && canPay(Integer.parseInt(lstTOP.get(controlType.getItemChoose()).getTypeCoin())) && CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                        message = "Phí tạo bài viết là " + lstTOP.get(controlType.getItemChoose()).getTypeCoin() + ". " +
                                "\nĐồng ý tạo bài viết?";
                        AlertDialogMessage(CreatePostActivity.this, "Xác nhận", message, "Đồng ý", "Không");
                    }
                } else {
                    // Update bài viết
                    if (canPay(20)) {
                        message = "Phí update bài viết là 20 coin" +
                                "\nĐồng ý update bài viết?";
                    }
                    AlertDialogMessage(CreatePostActivity.this, "Xác nhận", message, "Đồng ý", "Không");
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
        cbxTOP = findViewById(R.id.cbxTOP);
        cbxField = findViewById(R.id.cbxField);
        dtFrom = findViewById(R.id.dtFrom);
        dtTo = findViewById(R.id.dtTo);
        txtContentNote = findViewById(R.id.txtContentNote);
        txtContentNote.setText("\t Loại 1: Hiển thị ở mục bài viết hot của trang chủ." +
                "\n\t Loại 2: Uu tiên xuất hiện truớc khi tìm kiếm." +
                "\n\t Loại 3: Bình thường");

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

        FAHMessage.confirmBtnClick(CreatePostActivity.this);
    }

    private void addEvents() {
        progressDialog.show();
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
                initItem();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                Toast.makeText(CreatePostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        FirebaseDatabase.getInstance().getReference("TYPE_OF_POST").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                lstTOP = (List<TypeOfPost>) FAHQuery.GetDataObject(dataSnapshot, new TypeOfPost());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
                ToastMessage(CreatePostActivity.this, databaseError.getMessage());
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
    }

    private void initItem(){
        if (getIntent().getStringExtra("key") != null && !getIntent().getStringExtra("key").isEmpty()) {
            toolbar.setTitle("Chỉnh sửa bài viết");
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
                        controlType.setItemChoose(Integer.parseInt(dataUpdate.getTypeOfPost().getTypeID()) - 1);
                        status = dataUpdate.getStatus();
                        cbxTOP.setEnabled(false);
                        txtTitle.requestFocus();
                        progressDialog.dismiss();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CreatePostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
        } else {
            if (progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }
    }

    private boolean canPost(){
        check:
        if (txtTitle.getText().toString().equals("")) {
            txtTitle.requestFocus();
            Toast.makeText(this, "Tiêu đề không được để trống", Toast.LENGTH_SHORT).show();
            return false;
        } else if (controlField.getItemChoose() == -1) {
            cbxField.requestFocus();
            Toast.makeText(this, "Chưa chọn lĩnh vực hoạt động", Toast.LENGTH_SHORT).show();
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
        } else if (dtFrom.getText().toString().isEmpty()
                || Integer.parseInt(dtFrom.getText().toString()) < 0 || Integer.parseInt(dtFrom.getText().toString()) > 24) {
            dtFrom.requestFocus();
            Toast.makeText(this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (dtTo.getText().toString().isEmpty()
                || Integer.parseInt(dtTo.getText().toString()) < 0 || Integer.parseInt(dtTo.getText().toString()) > 24) {
            dtTo.requestFocus();
            Toast.makeText(this, "Thời gian không hợp lệ", Toast.LENGTH_SHORT).show();
            return false;
        } else if (controlSalary.getItemChoose() != 2 && !isValidSalary(controlSalary.getItemChoose())) {
            return false;
        } else  if (txtEmail.getText().toString().equals("") || !EmailValidator.isValid(txtEmail.getText().toString())) {
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

    private boolean canPay(int coin) {
        if (AccountData.userLogin.getCoin() > 0 && AccountData.userLogin.getCoin() >= coin) {
            return true;
        } else {
            ToastMessage(CreatePostActivity.this, "Không đủ coin để tạo bài viết");
            return false;
        }
    }

    @Override
    public void onYesClick() {
        TypeOfPost top = new TypeOfPost();
        top.setTypeID(cbxTOP.getText().toString().substring(5, 6));

        Category cgr = new Category();
        cgr.setKey(String.valueOf(controlField.getItemChoose() + 1));
        cgr.setCategoryID(String.valueOf(controlField.getItemChoose() + 1));
        cgr.setCategoryName(cbxField.getText().toString());

        // Create Post
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
                        userLogin.getKey());

                progressDialog.show();
                final String keyPost = FAHQuery.InsertData(dataUpdate);

                // update data Account: minus coin
                myRef = FirebaseDatabase.getInstance().getReference("TYPE_OF_POST").child(top.getTypeID());
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        int coinNew = userLogin.getCoin() - Integer.parseInt(dataSnapshot.getValue(TypeOfPost.class).getTypeCoin());
                        AccountData.userLogin.isCreatePost = true;
                        FAHQuery.UpdateData(coinNew, ExcuteString.GetUrlData("Account", userLogin.getKey(),"coin"));

                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }

                        ImformationDialogMessage(CreatePostActivity.this, "Xác nhận", "Tạo bài viết thành công\nVui lòng chờ addmin duyệt", "OK");
                        ArrayList<Notification> notice = new ArrayList<>();
                        for (int i = 0; i < 2; i++) {
                            Notification item = new Notification();
                            item.setKeyPost(keyPost);
                            if (i == 0) {
                                item.setAccountKey(AccountData.userLogin.getKey());
                                item.setTitle("Bạn đã tạo bài viết thành công");
                            }
                            else {
                                item.setAccountKey("-LftujxgRvYQDgsHnl9l");
                                item.setTitle(AccountData.userLogin.getAccountName() + " đã tạo bài viết");
                            }

                            notice.add(item);
                        }

                        FAHQuery.InsertData(notice, "Notification");
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        if (progressDialog.isShowing()) {
                            progressDialog.dismiss();
                        }
                    }
                });
            }
            catch (Exception e) {
                FAHMessage.ToastMessage(CreatePostActivity.this, e.getMessage());
                if (progressDialog.isShowing()) {
                    progressDialog.dismiss();
                }
            }
        } else {
            // Update Post
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
            dataUpdate.setStatus(0);
            FAHQuery.UpdateData(dataUpdate, "Post/" + getIntent().getStringExtra("key"));
            AccountData.userLogin.isCreatePost = true;
            FAHQuery.UpdateData(AccountData.userLogin.getCoin() - 20, ExcuteString.GetUrlData("Account", userLogin.getKey(),"coin"));

            ImformationDialogMessage(CreatePostActivity.this, "Xác nhận", "Đã cập nhật thành công\nVui lòng chờ addmin duyệt", "OK");

            ArrayList<Notification> notice = new ArrayList<>();
            int count = 2;
            int y = 0;
            if (dataUpdate.getListAccount() != null && dataUpdate.getListAccount().size() > 0) {
                count += dataUpdate.getListAccount().size();
            }

            for (int i = 0; i < count; i++) {
                Notification item = new Notification();
                item.setKeyPost(getIntent().getStringExtra("key"));
                switch (i) {
                    case 0:
                        item.setAccountKey(AccountData.userLogin.getKey());
                        item.setTitle("Bạn đã update bài viết thành công");
                        break;
                    case 1:
                        item.setAccountKey("-LftujxgRvYQDgsHnl9l");
                        item.setTitle(AccountData.userLogin.getAccountName() + " đã update bài viết");
                        break;
                    default:
                        item.setAccountKey(dataUpdate.getListAccount().get(y));
                        item.setTitle(AccountData.userLogin.getAccountName() + " đã update bài viết bạn đã ứng tuyển\n"
                            + dataUpdate.getTitlePost());
                        y++;
                        break;
                }

                notice.add(item);
            }

            FAHQuery.InsertData(notice, "Notification");
        }
    }

    private boolean isValidSalary(int choose) {
        if (choose == 0 && txtLuong1.getText().toString().equals("")) {
            txtLuong1.requestFocus();
            Toast.makeText(this, "Chưa nhập Lương", Toast.LENGTH_SHORT).show();
            return false;
        } else if (choose == 1) {
            if (txtLuong1.getText().toString().equals("")) {
                txtLuong1.requestFocus();
                Toast.makeText(this, "Chưa nhập Lương trong khoảng", Toast.LENGTH_SHORT).show();
                return false;
            } else if (txtLuong2.getText().toString().equals("")) {
                txtLuong2.requestFocus();
                Toast.makeText(this, "Chưa nhập Lương trong khoảng", Toast.LENGTH_SHORT).show();
                return false;
            } else if (Double.parseDouble(txtLuong1.getText().toString()) > Double.parseDouble(txtLuong2.getText().toString())) {
                txtLuong2.requestFocus();
                Toast.makeText(this, "Vui lòng nhập lương lớn hơn", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        return true;
    }

    @Override
    public void onOkClick() {
        AccountData.userLogin.isCreatePost = false;
        FAHMessage.unConfirmBtnClick();
        finish();
    }
}
