package com.example.fah.FAHScreen.User.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHData.CategoryData;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.IEvenItem;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.UserProfileChangeRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener{
   // ProgressDialog progressDoalog;
    EditText signUpNameEditTxt;
    EditText signUpEmailEditTxt;
    EditText signUppasswordEditTxt;
    EditText signUpConfirmPasswordEditTxt;
    TextView returnToLoginTextV;
    Button signUpBtn;
    Spinner spnListOfJob;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;
    private RadioButton radioButtontd;
    private RadioButton radioButtonuv;
    private LinearLayout layout_td;
    private LinearLayout layout_uv;
    static HashMap<Integer, String> spinnerMap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
            addControl();
            CategoryData.setUpCategoryData(new IEvenItem() {
                @Override
                public void callEvent() {
                    setTitlePostAdapter(CategoryData.categoryList);
                }
            });
            addEvent();
            createAuthStateListener();

    }

    private void addEvent() {
        radioButtontd.setOnClickListener(this);
        radioButtonuv.setOnClickListener(this);
        signUpBtn.setOnClickListener(this);
        returnToLoginTextV.setOnClickListener(this);

    }

    private void addControl() {
        signUpNameEditTxt =  findViewById(R.id.signUpNameEditText);
        signUpEmailEditTxt =  findViewById(R.id.signUpEmailEditText);
        signUppasswordEditTxt =  findViewById(R.id.signUpPasswordEditText);
        signUpConfirmPasswordEditTxt = findViewById(R.id.signUpConfirmPasswordEditText);
        returnToLoginTextV =  findViewById(R.id.loginReturnTextView);
        spnListOfJob = findViewById(R.id.spnListOfJob);
        signUpBtn = findViewById(R.id.signUpButton);
        radioButtontd = findViewById(R.id.radioButton_td);
        radioButtonuv  = findViewById(R.id.radioButton_uv);
        layout_td=findViewById(R.id.nhaTuyenDungLayout);
        layout_uv=findViewById(R.id.ungVienLayout);
        layout_uv.setVisibility(View.VISIBLE);
        layout_td.setVisibility(View.GONE);
    }
    /**
     * Set value for Adapter
     */
    private  void setTitlePostAdapter(ArrayList<Category> categoryList) {
        Toast.makeText(this, "lis="+CategoryData.categoryList, Toast.LENGTH_SHORT).show();
        List<String> listOfCategory = new ArrayList<>();
        spinnerMap = new HashMap<Integer, String>();
        spinnerMap.put(0, "All");
        listOfCategory.add("Tất cả");
        if (categoryList != null) {
            for (int i = 0; i < categoryList.size(); i++) {
                spinnerMap.put(i, categoryList.get(i).getCategoryID());
                listOfCategory.add(categoryList.get(i).getCategoryName());
            }
            ArrayAdapter<String> listOfPostAdapter =
                    new ArrayAdapter(
                            this, android.R.layout.simple_spinner_item,
                            listOfCategory);
            listOfPostAdapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
            spnListOfJob.setAdapter(listOfPostAdapter);
        }
    }

    @Override
    public void onClick(View v) {
        // default method for handling onClick Events..
        switch (v.getId()) {

            case R.id.signUpButton:
                createUserAccount();
                break;
            case R.id.radioButton_td:
                layout_td.setVisibility(View.VISIBLE);
                layout_uv.setVisibility(View.GONE);
                break;
            case R.id.radioButton_uv:
                layout_td.setVisibility(View.GONE);
                layout_uv.setVisibility(View.VISIBLE);
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
//
//    private  class LoadDataCategory extends AsyncTask<String, Void,  ArrayList<Category>> {
//        /** The system calls this to perform work in a worker thread and
//         * delivers it the parameters given to AsyncTask.execute() */
//        protected  ArrayList<Category> doInBackground(String... urls) {
//            CategoryData.setUpCategoryData();
//            return CategoryData.categoryList;
//        }
//
//        /** The system calls this to perform work in the UI thread and delivers
//         * the result from doInBackground() */
//        protected void onPostExecute(ArrayList<Category> result) {
//            setTitlePostAdapter(result);
//        }
//    }
}
