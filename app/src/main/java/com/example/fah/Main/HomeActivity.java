package com.example.fah.Main;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.fah.FAHScreen.Account.ManageAccountByAdminActivity;
import com.example.fah.FAHScreen.Account.ManageAccountByPostActivity;
import com.example.fah.FAHScreen.Account.Sample_Add_Edit_Delete_Account;
import com.example.fah.FAHScreen.Account.SearchAccountActivity;
import com.example.fah.FAHExcuteData.Adapters.TestLayoutAdapter;
import com.example.fah.FAHScreen.Main.DetailSearchActivity;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.FAHScreen.Manage.ManageCategoryActivity;
import com.example.fah.FAHScreen.Manage.ManageTypePost;
import com.example.fah.FAHExcuteData.Models.IEvenDialog;
import com.example.fah.FAHExcuteData.Models.TestLayout;
import com.example.fah.FAHScreen.Post.CreatePostActivity;
import com.example.fah.FAHScreen.Post.DetailPostActivity;
import com.example.fah.FAHScreen.Post.PostManagementActivity;
import com.example.fah.FAHScreen.User.Login.LoginActivity;
import com.example.fah.FAHScreen.User.PersionalImformationActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    ListView lvLayout;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        mAuth = FirebaseAuth.getInstance();
        addControl();
//          addEvent();
    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
    }

    private void addControl() {
            lvLayout=findViewById(R.id.lvActivity);
        ArrayList<TestLayout> layoutList = new ArrayList<TestLayout>();
        // add màn hình của mình ở đây để test
//        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"User Profile"));
//        layoutList.add(new TestLayout(HomeActivity.this, ProfileActivity.class,"Thông tin người dùng ThanhDC"));
//        layoutList.add(new TestLayout(HomeActivity.this, CreatePostActivity.class,"Tạo bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, PostManagementActivity.class,"Quản lý bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, DetailPostActivity.class,"Thông tin bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, DetailSearchActivity.class,"Tìm kiếm bài viết"));
//        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"Quản lý account"));
//        layoutList.add(new TestLayout(HomeActivity.this, MainActivity.class,"Main"));
        layoutList.add(new TestLayout(HomeActivity.this, MainActivity.class,"Main"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByAdminActivity.class,"Quản lý account"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageAccountByPostActivity.class,"Quản lý account theo bài viết"));
        layoutList.add(new TestLayout(HomeActivity.this, SearchAccountActivity.class,"Tìm kiếm account"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageCategoryActivity.class,"Quản lý danh mục"));
        layoutList.add(new TestLayout(HomeActivity.this, ManageTypePost.class,"Loại bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, CreatePostActivity.class,"Tạo bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, DetailPostActivity.class,"Chi tiết bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, PostManagementActivity.class,"Quản lý bài đăng"));
        layoutList.add(new TestLayout(HomeActivity.this, DetailSearchActivity.class,"Tìm kiếm bài viết"));
        layoutList.add(new TestLayout(HomeActivity.this, PersionalImformationActivity.class,"Profile user"));
        layoutList.add(new TestLayout(HomeActivity.this, Sample_Add_Edit_Delete_Account.class,"Sample Account"));
        layoutList.add(new TestLayout(HomeActivity.this, LoginActivity.class,"Login Activity"));
        layoutList.add(new TestLayout(HomeActivity.this, "Dialog Login", TestLayout.TYPE_DIALOG, new IEvenDialog() {
            @Override
            public void setEvent() {
                ShowDialogLogin();
            }
        }));
//        layoutList.add(new TestLayout(HomeActivity.this, ProfileActivity.class,"Main"));



        TestLayoutAdapter layoutByAdminAdapter = new TestLayoutAdapter(
                   HomeActivity.this,
                    R.layout.layout_test_item,
                layoutList);
        lvLayout.setAdapter(layoutByAdminAdapter);
    }

    private void ShowDialogLogin(){
        final Dialog dialog=new Dialog(HomeActivity.this);
        dialog.setContentView( R.layout.login_dialog);
        dialog.setTitle("Đăng Nhập");
        dialog.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*0.95),(int)(getResources().getDisplayMetrics().heightPixels*0.95));
        Button btn=dialog.findViewById(R.id.btnLogin);
        ImageView imageView=dialog.findViewById(R.id.btnBackLogin);
        final EditText txtEmail=dialog.findViewById(R.id.txtemail);
        final EditText txtpass=dialog.findViewById(R.id.txtpass);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Login(txtEmail.getText().toString(),txtpass.getText().toString());
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.setTitle("LOGIN ACTIVITY");
        dialog.setCancelable(true);
        dialog.show();
    }

    // dang nhap
    private void Login(String email, String password){
        final ProgressDialog pd = new ProgressDialog(HomeActivity.this);
        pd.setMessage("Đăng nhập");
        pd.show();
        // hàm đăng nhập overrie của firebase
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    boolean flag=false;
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //nếu đăng nhập thành công
                            Toast.makeText(HomeActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                            flag=true;
                        } else {
                            // nếu đăng nhập thất bại
                            Toast.makeText(HomeActivity.this, "Đăng Nhập thất bại", Toast.LENGTH_SHORT).show();
                        }

                        // bắt đầu thực thi thất bại
                        if (!task.isSuccessful()) {
                            //  pd.dismiss();
                        }

                        // [END_EXCLUDE]
                        pd.dismiss();

                    }
                });
    }

}
