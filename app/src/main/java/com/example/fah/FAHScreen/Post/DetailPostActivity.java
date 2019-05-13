package com.example.fah.FAHScreen.Post;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.widget.TableRow;

import com.example.fah.FAHCommon.FAHConnection.CheckWifi;
import com.example.fah.FAHCommon.FAHControl.FAHMessage;
import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Post;
import com.example.fah.FAHScreen.Account.ManageAccountByPostActivity;
import com.example.fah.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static com.example.fah.FAHCommon.FAHControl.FAHMessage.AlertDialogMessage;
import static com.example.fah.FAHCommon.FAHControl.FAHMessage.ToastMessage;
import static com.example.fah.FAHData.AccountData.userLogin;

import java.util.ArrayList;
import java.util.Date;

public class DetailPostActivity extends AppCompatActivity implements IConfirmClick {

    Toolbar toolbar;

    TextView txtPic;
    TextView txtTitle;
    TextView txtCompanyName;
    TextView txtTime;
    TextView txtNumber;
    TextView txtPhone;
    TextView txtAddress;
    TextView txtCategory;
    TextView txtSalary;
    TextView txtDeadline;
    TextView txtDescription1;
    TextView txtDescription2;
    TextView txtRequired1;
    TextView txtRequired2;
    TextView txtQuyenLoi2;
    TextView txtQuyenLoi1;

    TextView txtSubmitMember;
    Button btnSubmit;
    TableRow txtSubmit;
    Button btnDetailSubmit;

    Post data;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_post);

        addControls();
        addEvents();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (userLogin != null) {
            if (userLogin.getRole() == 2 && userLogin.getKey().equals(getIntent().getStringExtra("keyAccount"))) {
                getMenuInflater().inflate(R.menu.btn_edit, menu);
            } else if (userLogin.getRole() == 3) {
                getMenuInflater().inflate(R.menu.btn_del, menu);
            }
        }

        return true;
    }

    private void addEvents() {
        FirebaseDatabase.getInstance().getReference().child("Post")
                .child(getIntent().getStringExtra("key")).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                data = dataSnapshot.getValue(Post.class);
                if (data == null) return;

                // Get Account
                FirebaseDatabase.getInstance().getReference().child("Account")
                        .child(data.getKeyAccount()).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Account creator = dataSnapshot.getValue(Account.class);

                        // Set data
                        String salary = data.getTypeOfSalary() == 2 ? "Thỏa thuận"
                                : data.getTypeOfSalary() == 0 ? data.getSalary_from() : data.getSalary_from() + " ~ " + data.getSalary_to();

                        txtPic.setText("Pic: " + creator.getAccountName());
                        txtTitle.setText(data.getTitlePost());
                        txtCompanyName.setText(data.getCompanyName());
                        txtCategory.setText(data.getCategory().getCategoryName());
                        txtTime.setText(data.getDtFrom() + " Giờ Đến " + data.getDtTo() + " Giờ");
                        txtNumber.setText(data.getSoLuong());
                        txtPhone.setText(data.getPhone());
                        txtAddress.setText(data.getAddress());
                        txtSalary.setText(salary);
                        txtDeadline.setText(data.getDeadLine());
                        txtDescription2.setText(data.getJobDescription());
                        txtRequired2.setText(data.getRequired());
                        txtQuyenLoi2.setText(data.getBenifit());

                        if (userLogin != null) {
                            if (userLogin.getRole() == 1) {
                                if (userLogin.getKey().equals(creator.getKey())) {
                                    btnSubmit.setVisibility(View.GONE);
                                } else {
                                    btnSubmit.setVisibility(View.VISIBLE);
                                    if (data.getListAccount() != null && data.getListAccount().size() > 0
                                            && data.getListAccount().contains(userLogin.getKey())) {
                                        btnSubmit.setText("Đã ứng tuyển");
                                    } else {
                                        btnSubmit.setText("Ứng tuyển");
                                    }
                                }
                            } else if (userLogin.getRole() == 3) {
                                btnSubmit.setText(data.getStatus() == 1 ? "Đã duyệt" : "Duyệt");
                                btnSubmit.setEnabled(data.getStatus() == 0);
                            } else if (userLogin.getRole() == 2) {
                                if (userLogin.getKey().equals(creator.getKey())) {
                                    txtSubmit.setVisibility(View.VISIBLE);
                                    if (data.getListAccount() != null && data.getListAccount().size() > 0) {
                                        txtSubmitMember.setText(String.valueOf(data.getListAccount().size()));
                                    } else {
                                        txtSubmitMember.setText("0");
                                    }
                                } else {
                                    txtSubmit.setVisibility(View.GONE);
                                }
                            }
                        } else {
                            btnSubmit.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(DetailPostActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (btnSubmit.getText().equals("Ứng tuyển")) {
                    if (data.getListAccount() == null) {
                        data.setListAccount(new ArrayList<String>());
                    }

                    itemId = R.id.btnSubmit;
                    AlertDialogMessage(DetailPostActivity.this, "Xác nhận", "Bạn có thực sự muốn ứng tuyển", "Đồng ý", "Không");

                } else if (btnSubmit.getText().equals("Đã ứng tuyển")) {
                    try {
                        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                            data.getListAccount().remove(userLogin.getKey());
                            FAHQuery.UpdateData(data, data.getClass().getSimpleName() + "/" + getIntent().getStringExtra("key"));
                        }
                    } catch (Exception ex) {
                        ToastMessage(DetailPostActivity.this, ex.getMessage());
                    }
                } else if (btnSubmit.getText().equals("Duyệt")) {
                    if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                        data.setStatus(1);
                        data.setApproveDate(new Date());
                        FAHQuery.UpdateData(data, data.getClass().getSimpleName() + "/" + data.getKey());
                    }
                }
            }
        });

        btnDetailSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DetailPostActivity.this, ManageAccountByPostActivity.class);
                intent.putExtra("key", data.getKey());
                startActivity(intent);
                finish();
            }
        });
    }

    private void addControls() {
        // toolbar
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_backspace_black);
        toolbar.setTitle("Chi tiết bài viết");
        setSupportActionBar(toolbar);

        txtPic = findViewById(R.id.txtPic);
        txtTitle = findViewById(R.id.txtTitle);
        txtCompanyName = findViewById(R.id.txtCompanyName);
        txtTime = findViewById(R.id.txtTime);
        txtNumber = findViewById(R.id.txtNumber);
        txtPhone = findViewById(R.id.txtPhone);
        txtAddress = findViewById(R.id.txtAddress);
        txtCategory = findViewById(R.id.txtCategory);
        txtSalary = findViewById(R.id.txtSalary);
        txtDeadline = findViewById(R.id.txtDeadline);
        txtDescription1 = findViewById(R.id.txtDescription1);
        txtRequired1 = findViewById(R.id.txtRequired1);
        txtQuyenLoi1 = findViewById(R.id.txtQuyenLoi1);
        btnSubmit = findViewById(R.id.btnSubmit);

        txtDescription2 = findViewById(R.id.txtDescription2);
        txtDescription2.setVisibility(View.GONE);
        txtRequired2 = findViewById(R.id.txtRequired2);
        txtRequired2.setVisibility(View.GONE);
        txtQuyenLoi2 = findViewById(R.id.txtQuyenLoi2);
        txtQuyenLoi2.setVisibility(View.GONE);

        txtSubmit = findViewById(R.id.txtSubmit);
        btnDetailSubmit = findViewById(R.id.btnDetailSubmit);
        txtSubmitMember = findViewById(R.id.txtSubmitMember);

        FAHMessage.confirmBtnClick(DetailPostActivity.this);
    }

    public void onToggleJob(View v) {
        if (txtDescription2.isShown()) {
            txtDescription2.setVisibility(View.GONE);
            txtDescription1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtDescription2.setVisibility(View.VISIBLE);
            txtDescription1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }

    public void onToggleRequired(View v) {
        if (txtRequired2.isShown()) {
            txtRequired2.setVisibility(View.GONE);
            txtRequired1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtRequired2.setVisibility(View.VISIBLE);
            txtRequired1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }

    public void onToggleQuyenLoi(View v) {
        if (txtQuyenLoi2.isShown()) {
            txtQuyenLoi2.setVisibility(View.GONE);
            txtQuyenLoi1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_right, 0);
        } else {
            txtQuyenLoi2.setVisibility(View.VISIBLE);
            txtQuyenLoi1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_arrow_down, 0);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home: {
                finish();
                return true;
            }
            case R.id.btnEdit: {
                Intent intent = new Intent(DetailPostActivity.this, CreatePostActivity.class);
                intent.putExtra("key", getIntent().getStringExtra("key"));
                startActivity(intent);
                return true;
            }
            case R.id.btnDel: {
                if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
                    AlertDialogMessage(DetailPostActivity.this, "Xác nhận", "Bạn có thực sự muốn xóa ?", "Có", "Không");
                }
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);
            }
        }
    }

    @Override
    public void onYesClick() {
        if (CheckWifi.isConnect((TextView) findViewById(R.id.isConnect))) {
            if (itemId == R.id.btnSubmit) {
                try {
                    data.getListAccount().add(userLogin.getKey());
                    FAHQuery.UpdateData(data, data.getClass().getSimpleName() + "/" + getIntent().getStringExtra("key"));
                } catch (Exception ex) {
                    ToastMessage(DetailPostActivity.this, ex.getMessage());
                }
            } else if (itemId == R.id.btnDel) {
                try {
                    FAHQuery.DeleteData(new String[]{data.getClass().getSimpleName() + "/" + data.getKey()});
                } catch (Exception ex) {
                    ToastMessage(DetailPostActivity.this, ex.getMessage());
                }
                finish();
            }

        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        FAHMessage.unConfirmBtnClick();
    }
}
