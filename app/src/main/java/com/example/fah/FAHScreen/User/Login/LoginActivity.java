package com.example.fah.FAHScreen.User.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText emailEditText;
    EditText passwordEditText;
    Button loginBtn;
    TextView resetBtn;
    TextView createAccountBtn;
    AccountData accountData=new AccountData();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
         if (MainActivity.userLogin.isLogin() ==true){
            nextMain();
        }else{
            emailEditText = (EditText) findViewById(R.id.emailEditText);
            passwordEditText = (EditText) findViewById(R.id.passwordEditText);
            loginBtn = (Button) findViewById(R.id.loginbutton);
            loginBtn.setOnClickListener(this);
            resetBtn = (TextView) findViewById(R.id.resetPwTextView);
            resetBtn.setOnClickListener(this);
            createAccountBtn = (TextView) findViewById(R.id.createAccTextView);
            createAccountBtn.setOnClickListener(this);
        }
    }

    private void nextMain() {
        Intent i = new Intent(LoginActivity.this, MainActivity.class);
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

    public void loginUser(){

        String email = emailEditText.getText().toString().trim();
        String password  = passwordEditText.getText().toString().trim();


        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }

        accountData.getFirebaseAuth().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final   Account account = new Account();
                          //  Toast.makeText(LoginActivity.this, "email="+AccountData.firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
                           AccountData.firebaseUser = accountData.getFirebaseAuth().getCurrentUser();
                            FirebaseDatabase.getInstance().getReference().child("Account")
                                    .orderByChild("email")
                                    .equalTo(AccountData.firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                    try {
                                        JSONObject obj = new JSONObject(dataSnapshot.getValue().toString());
                                        JSONObject arr=obj.getJSONObject(obj.names().get(0).toString());
                                        account.setAccountID(!arr.isNull("accountID")?arr.getString("accountID"):null);
                                        account.setAccountName(!arr.isNull("accountName")?arr.getString("accountName"):null);
                                        account.setSex(!arr.isNull("sex")?arr.getString("sex"):null);
                                        account.setDateOfBirth(!arr.isNull("dateOfBirth")?arr.getString("dateOfBirth"):null);
                                        account.setAddress(!arr.isNull("address")?arr.getString("address"):null);
                                        account.setPhone(!arr.isNull("phone")?arr.getString("phone"):null);
                                        account.setEmail(!arr.isNull("email")?arr.getString("email"):null);
                                        account.setRole(!arr.isNull("role")?Integer.parseInt(arr.getString("role")):null);
                                        account.setCompanyName(!arr.isNull("companyName")?arr.getString("companyName"):null);
                                        account.setCompanyAddress(!arr.isNull("companyAddress")?arr.getString("companyAddress"):null);
                                        account.setCompanyPhone(!arr.isNull("companyPhone")?arr.getString("companyPhone"):null);
                                        account.setCompanyEmail(!arr.isNull("companyEmail")?arr.getString("companyEmail"):null);
                                        account.setCompanyIntro(!arr.isNull("companyIntro")?arr.getString("companyIntro"):null);
                                        account.setCoin(!arr.isNull("coin")?Integer.parseInt(arr.getString("coin")):null);
                                        account.setStatusBlock(!arr.isNull("statusBlock")?Integer.parseInt(arr.getString("statusBlock")):null);
                                        account.setLogin(true);
                                        account.setStatusBlock(!arr.isNull("statusSendInvation")?Integer.parseInt(arr.getString("statusSendInvation")):null);
                                        account.setKey(!arr.isNull("key")?arr.getString("key"):null);
                                        MainActivity.userLogin = account;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                        Toast.makeText(LoginActivity.this, "Lỗi:"+e, Toast.LENGTH_SHORT).show();
                                    }
                                    nextMain();
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });


                        } else {
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            // updateUI(null);
                        }
                    }
                });
    }

}
