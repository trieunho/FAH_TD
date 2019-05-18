package com.example.fah.FAHScreen.User.Login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    TextView resetBtn;
    TextView createAccountBtn;
    ProgressDialog progressDoalog;
    LinearLayout ActivityLoginLayout;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        progressDoalog = new ProgressDialog(LoginActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Đang vào hệ thống....");
        progressDoalog.setTitle("Đăng Nhập");
        if (AccountData.userLogin != null) {
            if (AccountData.userLogin.isLogin() == true) {
                nextMain();
            } else {
                addControl();
            }
        } else {
//            AccountData.setUpAccountData();
            addControl();
        }
    }

    private void addControl() {
        ActivityLoginLayout = findViewById(R.id.ActivityLoginLayout);
        ActivityLoginLayout.setMinimumWidth(200);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        loginBtn = (Button) findViewById(R.id.loginbutton);
        loginBtn.setOnClickListener(this);
        resetBtn = (TextView) findViewById(R.id.resetPwTextView);
        resetBtn.setOnClickListener(this);
        createAccountBtn = (TextView) findViewById(R.id.createAccTextView);
        createAccountBtn.setOnClickListener(this);
    }

    private void nextMain() {
        AccountData.setUpAccountData();
        if (AccountData.userLogin.getStatusBlock() == 1) {
            Toast.makeText(this, "Tài khoản này đã bị khóa, vui lòng liên hệ quản trị viên để mở hoạt dộng.", Toast.LENGTH_SHORT).show();
            return;
        } else {
            if (getIntent() != null && getIntent().getStringExtra("flag") != null && getIntent().getStringExtra("flag").equals("detail")) {
                finish();
            } else {
                if (!AccountData.userLogin.isSignOut) {
                    AccountData.userLogin.setLogin(true);
                    AccountData.userLogin.isSignOut = false;
                }

                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();
            }
        }
    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.loginbutton:
                loginUser();
                break;

            case R.id.createAccTextView:
                Intent i2 = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(i2);
                finish();
                break;

            case R.id.resetPwTextView:
                Intent i3 = new Intent(LoginActivity.this, ResetPasswordActivity.class);
                startActivity(i3);
                finish();
                break;

            default:
                break;
        }
    }

    private boolean validate(String emailStr, String password) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return (password.length() > 0 || password.equals(";")) && matcher.find();
    }

    public void loginUser() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        if (email != "" && email != null) {
            email = email.trim();
        }
        if (password != "" && password != null) {
            password = password.trim();
        }
        if (!validate(email, password)) {
            Toast.makeText(this, "Email hoặc mật khẩu không hợp lệ.", Toast.LENGTH_SHORT).show();
            emailEditText.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_LONG).show();
            return;
        }
        progressDoalog.show();
        AccountData.firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                new AccountData().GetAccount(new IEventData() {
                                    @Override
                                    public void EventSuccess() {
                                        nextMain();

                                        progressDoalog.dismiss();
                                    }

                                    @Override
                                    public void EventFail(String message) {
                                        Toast.makeText(LoginActivity.this, message, Toast.LENGTH_SHORT).show();
                                        progressDoalog.dismiss();
                                    }
                                });
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this, "Lỗi khi tải thông tin người dùng.", Toast.LENGTH_SHORT).show();
                                progressDoalog.dismiss();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập không hợp lệ.",
                                    Toast.LENGTH_SHORT).show();
                            progressDoalog.dismiss();
                        }
                    }
                });
    }

}
