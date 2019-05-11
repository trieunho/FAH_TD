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
import com.example.fah.FAHModel.Models.IEventData;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {
    // ProgressDialog progressDoalog;
    private EditText signUpNameEditTxt,
            signUpDateOfBirthtd,
            signUpDateOfBirthuv,
            signUpcompanyName,
            signUpcompanyAddress,
            signUpcompanyPhone,
            signUpcompanyEmail,
            signUpAddressuv,
            signUpPhoneuv,
            signUpFromTimeuv,
            signUpToTimeuv, signUpEmailEditTxt, signUppasswordEditTxt, signUpConfirmPasswordEditTxt;
    private RadioButton radioButtontd,
            radioButton_sexNamtd,
            radioButton_sexNutd,
            radioButton_sexNamuv,
            radioButton_sexNuuv, radioButtonuv;
    private final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    private TextView returnToLoginTextV;
    private Button signUpBtn;
    private Spinner spnListOfJob;
    private LinearLayout layout_td;
    private LinearLayout layout_uv;
    static HashMap<Integer, String> spinnerMap;
    private FirebaseAuth.AuthStateListener firebaseAuthListener;

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
        //candidate control
        signUpDateOfBirthuv = findViewById(R.id.signUpDateOfBirthuv);
        signUpAddressuv = findViewById(R.id.signUpAddressuv);
        signUpPhoneuv = findViewById(R.id.signUpPhoneuv);
        signUpFromTimeuv = findViewById(R.id.signUpFromTimeuv);
        signUpToTimeuv = findViewById(R.id.signUpToTimeuv);
        radioButton_sexNamuv = findViewById(R.id.radioButton_sexNamuv);
        radioButton_sexNuuv = findViewById(R.id.radioButtonsex_Nuuv);
        spnListOfJob = findViewById(R.id.spnListOfJob);
        //employer control
        signUpDateOfBirthtd = findViewById(R.id.signUpDateOfBirthtd);
        signUpcompanyName = findViewById(R.id.signUpcompanyName);
        signUpcompanyAddress = findViewById(R.id.signUpcompanyAddress);
        signUpcompanyPhone = findViewById(R.id.signUpcompanyPhone);
        signUpcompanyEmail = findViewById(R.id.signUpcompanyEmail);
        radioButton_sexNamtd = findViewById(R.id.radioButton_sexNamtd);
        radioButton_sexNutd = findViewById(R.id.radioButtonsex_Nutd);
        // general control
        signUpNameEditTxt = findViewById(R.id.signUpNameEditText);
        signUpEmailEditTxt = findViewById(R.id.signUpEmailEditText);
        signUppasswordEditTxt = findViewById(R.id.signUpPasswordEditText);
        signUpConfirmPasswordEditTxt = findViewById(R.id.signUpConfirmPasswordEditText);
        returnToLoginTextV = findViewById(R.id.loginReturnTextView);
        signUpBtn = findViewById(R.id.signUpButton);
        radioButtontd = findViewById(R.id.radioButton_td);
        radioButtonuv = findViewById(R.id.radioButton_uv);
        layout_td = findViewById(R.id.nhaTuyenDungLayout);
        layout_uv = findViewById(R.id.ungVienLayout);
        layout_uv.setVisibility(View.VISIBLE);
        layout_td.setVisibility(View.GONE);
    }

    /**
     * Set value for Adapter
     */
    private void setTitlePostAdapter(ArrayList<Category> categoryList) {
        List<String> listOfCategory = new ArrayList<>();
        spinnerMap = new HashMap<Integer, String>();
        spinnerMap.put(0, "All");
        listOfCategory.add("Chọn");
        if (categoryList != null) {
            for (int i = 0; i < categoryList.size(); i++) {
                spinnerMap.put((i + 1), categoryList.get(i).getCategoryID());
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

    public void createUserAccount() {
        final String createName = signUpNameEditTxt.getText().toString().trim();
        final String createEmail = signUpEmailEditTxt.getText().toString().trim();
        String createPassword = signUppasswordEditTxt.getText().toString().trim();
        String createConfrimPassword = signUpConfirmPasswordEditTxt.getText().toString().trim();
        if (TextUtils.isEmpty(createName)) {
            Toast.makeText(this, "Vui lòng nhập tên.", Toast.LENGTH_LONG).show();
            signUpNameEditTxt.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(createEmail)) {
            Toast.makeText(this, "Vui lòng nhập Email.", Toast.LENGTH_LONG).show();
            signUpEmailEditTxt.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(createPassword)) {
            Toast.makeText(this, "Vui lòng nhập mật khảu.", Toast.LENGTH_LONG).show();
            signUppasswordEditTxt.requestFocus();
            return;
        }
        if (!createPassword.equals(createConfrimPassword)) {
            Toast.makeText(this, "Mật khẩu không khớp.", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!validate(createEmail, createPassword)) {
            Toast.makeText(this, "Email hoặc mật khẩu không hợp lệ.", Toast.LENGTH_SHORT).show();
            signUpConfirmPasswordEditTxt.requestFocus();
            return;
        }
        if (radioButtonuv.isChecked()) {
            if (!ValidateUV()) return;
        } else {
            if (!ValidateTD()) return;
        }

        AccountData.firebaseAuth.createUserWithEmailAndPassword(createEmail, createPassword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            AccountData.firebaseUser = AccountData.firebaseAuth.getCurrentUser();
                            if (AccountData.firebaseUser != null) {
                                UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                                        .setDisplayName(createName)
                                        .build();
                                AccountData.firebaseUser.updateProfile(profileUpdates)
                                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                if (task.isSuccessful()) {
                                                    final Account account = new Account();
                                                    account.setAccountName(AccountData.firebaseUser.getDisplayName());
                                                    account.setEmail(AccountData.firebaseUser.getEmail());
                                                    if (radioButtonuv.isChecked()) {
                                                        account.setRole(1);
                                                        account.setPhone(signUpPhoneuv.getText().toString());
                                                        account.setAddress(signUpAddressuv.getText().toString());
                                                        account.setStatusBlock(0);
                                                        account.setCoin(0);
                                                        account.setDateOfBirth(signUpDateOfBirthuv.getText().toString());
                                                        account.setSex(radioButton_sexNamuv.isChecked() ? "Nam" : "Nữ");
                                                        account.setCategory(new Category(spinnerMap.get(spnListOfJob.getSelectedItemPosition()), spnListOfJob.getSelectedItem().toString()));
                                                        account.setDtTo(Integer.parseInt(signUpToTimeuv.getText().toString()));
                                                        account.setDtFrom(Integer.parseInt(signUpFromTimeuv.getText().toString()));

                                                    } else {
                                                        account.setRole(2);
                                                        account.setStatusBlock(0);
                                                        account.setCoin(0);
                                                        account.setSex(radioButton_sexNamtd.isChecked() ? "Nam" : "Nữ");
                                                        account.setDateOfBirth(signUpDateOfBirthtd.getText().toString());
                                                        account.setCompanyEmail(signUpcompanyEmail.getText().toString());
                                                        account.setCompanyName(signUpcompanyName.getText().toString());
                                                        account.setCompanyPhone(signUpcompanyPhone.getText().toString());
                                                        account.setCompanyAddress(signUpcompanyAddress.getText().toString());
                                                        account.setCompanyIntro(signUpcompanyName.getText().toString());
                                                    }
                                                    String key = AccountData.InsertAccountGetKey(account);
                                                    account.setKey(key);
                                                    AccountData.UpdateAccount(account, new IEventData() {
                                                        @Override
                                                        public void EventSuccess() {
                                                            AccountData.userLogin = account;
                                                            AccountData.userLogin.setLogin(true);
                                                            startActivity(new Intent(SignupActivity.this, MainActivity.class));
                                                            finish();
                                                        }
                                                        @Override
                                                        public void EventFail(String message) {
                                                            Toast.makeText(SignupActivity.this, message, Toast.LENGTH_SHORT).show();
                                                        }
                                                    });

                                                }
                                            }
                                        });
                            }

                        } else {
                            Toast.makeText(SignupActivity.this, "Đăng ký gặp sự cố.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    private boolean validate(String emailStr, String password) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return (password.length() > 0 || password.equals(";")) && matcher.find();
    }

    private boolean ValidateTD() {
        if (TextUtils.isEmpty(signUpDateOfBirthtd.getText())) {
            Toast.makeText(this, "Vui lòng nhập ngày sinh.", Toast.LENGTH_LONG).show();
            signUpDateOfBirthtd.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpcompanyName.getText())) {
            Toast.makeText(this, "Vui lòng nhập tên công ty.", Toast.LENGTH_LONG).show();
            signUpcompanyName.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpcompanyAddress.getText())) {
            Toast.makeText(this, "Vui lòng nhập địa chỉ.", Toast.LENGTH_LONG).show();
            signUpcompanyAddress.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpcompanyPhone.getText())) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại công ty.", Toast.LENGTH_LONG).show();
            signUpcompanyPhone.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpcompanyEmail.getText())) {
            Toast.makeText(this, "Vui lòng nhập email công ty.", Toast.LENGTH_LONG).show();
            signUpcompanyEmail.requestFocus();
            return false;
        }
        return true;

    }

    private boolean ValidateUV() {
        if (TextUtils.isEmpty(signUpDateOfBirthuv.getText())) {
            Toast.makeText(this, "Vui lòng nhập ngày sinh.", Toast.LENGTH_LONG).show();
            signUpDateOfBirthuv.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpAddressuv.getText())) {
            Toast.makeText(this, "Vui lòng nhập đại chỉ.", Toast.LENGTH_LONG).show();
            signUpAddressuv.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpPhoneuv.getText())) {
            Toast.makeText(this, "Vui lòng nhập số điện thoại.", Toast.LENGTH_LONG).show();
            signUpPhoneuv.requestFocus();
            return false;
        }
        if (TextUtils.isEmpty(signUpFromTimeuv.getText())) {
            Toast.makeText(this, "Vui lòng nhập thời gian làm việc.", Toast.LENGTH_LONG).show();
            signUpFromTimeuv.requestFocus();
            return false;
        } else {
            try {
                int totime = Integer.parseInt(signUpFromTimeuv.getText().toString());
                if (totime > 23 || totime < 0 || (Double.parseDouble(signUpFromTimeuv.getText().toString()) - totime) > 0)
                    throw new Exception();
            } catch (Exception e) {
                Toast.makeText(this, "Sai kiểu thời gian(0-23)", Toast.LENGTH_SHORT).show();
                signUpFromTimeuv.requestFocus();
                return false;
            }
        }
        if (TextUtils.isEmpty(signUpToTimeuv.getText())) {
            Toast.makeText(this, "Vui lòng nhập thời gian làm việc.", Toast.LENGTH_LONG).show();
            signUpToTimeuv.requestFocus();
            return false;
        } else {
            try {
                int totime = Integer.parseInt(signUpToTimeuv.getText().toString());
                if (totime > 23 || totime < 0 || (Double.parseDouble(signUpToTimeuv.getText().toString()) - totime) > 0)
                    throw new Exception();
            } catch (Exception e) {
                Toast.makeText(this, "Sai kiểu thời gian(0-23)", Toast.LENGTH_SHORT).show();
                signUpToTimeuv.requestFocus();
                return false;
            }
        }
        if (spnListOfJob.getSelectedItemPosition() == 0) {
            Toast.makeText(this, "Vui lòng chọn công việc.", Toast.LENGTH_LONG).show();
            spnListOfJob.requestFocus();
            return false;
        }
        return true;
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
