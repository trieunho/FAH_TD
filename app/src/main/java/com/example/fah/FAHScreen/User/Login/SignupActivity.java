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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{

    EditText signUpNameEditTxt;
    EditText signUpEmailEditTxt;
    EditText signUppasswordEditTxt;
    EditText signUpConfirmPasswordEditTxt;
    TextView returnToLoginTextV;
    Button signUpBtn;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        signUpNameEditTxt = (EditText) findViewById(R.id.signUpNameEditText);
        signUpEmailEditTxt = (EditText) findViewById(R.id.signUpEmailEditText);
        signUppasswordEditTxt = (EditText) findViewById(R.id.signUpPasswordEditText);
        signUpConfirmPasswordEditTxt = (EditText) findViewById(R.id.signUpConfirmPasswordEditText);
        returnToLoginTextV = (TextView) findViewById(R.id.loginReturnTextView);
        returnToLoginTextV.setOnClickListener(this);
        signUpBtn = (Button) findViewById(R.id.signUpButton);
        signUpBtn.setOnClickListener(this);

        createAuthStateListener();

    }


    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.signUpButton:
                createUserAccount();
                break;

            case R.id.loginReturnTextView:
                Intent i2 = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(i2);
                finish();
                break;

            default:
                break;
        }
    }


    public void createUserAccount(){
        final String createName = signUpNameEditTxt.getText().toString().trim();
        final String createEmail = signUpEmailEditTxt.getText().toString().trim();
        String createPassword = signUppasswordEditTxt.getText().toString().trim();
        String createConfrimPassword = signUpConfirmPasswordEditTxt.getText().toString().trim();



        if(TextUtils.isEmpty(createEmail)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(createPassword)){
            Toast.makeText(this,"Please enter password", Toast.LENGTH_LONG).show();
            return;
        }



        AccountData.firebaseAuth.createUserWithEmailAndPassword(createEmail, createPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            AccountData.firebaseUser=AccountData.firebaseAuth.getCurrentUser();
                            if(AccountData.firebaseUser!=null){
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(createName)
                                        .build();

                                AccountData.firebaseUser.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    Account account =new Account();
                                                    account.setAccountName(AccountData.firebaseUser.getDisplayName());
                                                    account.setEmail(AccountData.firebaseUser.getEmail());
                                                    String key = AccountData.InsertAccountGetKey(account);
                                                    account.setKey(key);
                                                    AccountData.UpdateAccount(account);
                                                    MainActivity.userLogin = account;
                                                    MainActivity.userLogin.setLogin(true);
                                                    startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                    finish();
                                                }
                                            }
                                        });
                            }

                        } else {
                            // If sign in fails, display a message to the user.

                            Toast.makeText(SignupActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    private void createAuthStateListener() {
        firebaseAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if (AccountData.firebaseUser != null) {
                    Intent intent = new Intent(SignupActivity.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }
            }

        };
    }


    @Override
    public void onStart() {
        super.onStart();
        AccountData.firebaseAuth.addAuthStateListener(firebaseAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (firebaseAuthListener != null) {
            AccountData.firebaseAuth.removeAuthStateListener(firebaseAuthListener);
        }
    }


}
