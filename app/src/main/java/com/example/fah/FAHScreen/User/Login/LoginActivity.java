package com.example.fah.FAHScreen.User.Login;

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

import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHScreen.User.ProfileActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    TextView resetBtn;
    TextView createAccountBtn;
    LinearLayout ActivityLoginLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        if (AccountData.userLogin != null) {
            if (AccountData.userLogin.isLogin() == true) {
                nextMain();
            } else {
                addControl();
            }
        } else {
            AccountData.setUpAccountData();
            addControl();
        }
    }

    private void addControl() {
        ActivityLoginLayout=findViewById(R.id.ActivityLoginLayout);
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
        Intent i = new Intent(LoginActivity.this, ProfileActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
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

    public void loginUser() {

        String email = emailEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Vui lòng nhập Email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Vui lòng nhập mật khẩu", Toast.LENGTH_LONG).show();
            return;
        }

        AccountData.firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            try {
                                AccountData.GetAccount(new IEventData() {
                                    @Override
                                    public void EventSuccess() {
                                        nextMain();
                                    }

                                    @Override
                                    public void EventFail() {
                                        Toast.makeText(LoginActivity.this, "Lỗi không thể lấy được thông tin người dùng!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                Toast.makeText(LoginActivity.this, "Lỗi khi tải thông tin người dùng.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(LoginActivity.this, "Đăng nhập không hợp lệ.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

}
