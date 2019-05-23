package com.example.fah.FAHScreen.User;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.CommonUtils.ImageUtils;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHData.CategoryData;
import com.example.fah.FAHData.ImageData;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Category;
import com.example.fah.FAHModel.Models.IEvenItem;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.Image;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

public class ProfileActivity extends AppCompatActivity {
    TextView userName,userEmail,
            sex,dateOfBirth,address,phone,
            companyName,companyAddress,companyPhone
            ,companyEmail,
            companyIntro,dtFrom,dtTo;
    ImageView userAvata,edit_profile_btn;
    ProgressDialog progressDoalog,progressDoalogupdate;
    LinearLayout profileCompanyLayout,profileTimeWorkLayout;
    Toolbar toolbar;
    TextView txtuser_profile_name,txtuser_profile_short_bio;
    static HashMap<Integer, String> spinnerMap;
    private Spinner updateProfilespnListOfJob;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        progressDoalog = new ProgressDialog(ProfileActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Đang tải....");
        progressDoalog.setTitle("Tải ảnh lên");
        progressDoalogupdate = new ProgressDialog(ProfileActivity.this);
        progressDoalogupdate.setMax(100);
        progressDoalogupdate.setMessage("Đang cập nhập....");
        progressDoalogupdate.setTitle("Cập nhập thông tin.");

        addControl();
        addEvent();
    }

    private void addEvent() {
        edit_profile_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     ShowDialogUpdate();
            }
        });
        userAvata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(ProfileActivity.this)
                        .setTitle("Avatar")
                        .setMessage("Bạn có muốn thay đổi ảnh đại diện?")
                        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent = new Intent();
                                intent.setType("image/*");
                                intent.setAction(Intent.ACTION_PICK);
                                startActivityForResult(Intent.createChooser(intent, "Chọn ảnh"), 1994);
                                dialogInterface.dismiss();

                            }
                        })
                        .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        }).show();
            }
        });
    }

    private void ShowDialogUpdate() {
        final Dialog dialog=new Dialog(ProfileActivity.this);
        dialog.setContentView( R.layout.activity_update_profile);
        dialog.setTitle("THÔNG TIN");
        TextView profileReturnTextView=dialog.findViewById(R.id.updateProfileReturnTextView);
        Button updateProfileButton = dialog.findViewById(R.id.updateProfileButton);
        // bindding control dialog
        // TD
        EditText updateProfileName=dialog.findViewById(R.id.updateProfileName);
        EditText updateProfileEmail=dialog.findViewById(R.id.updateProfileEmail);
        RadioButton updateProfileradioButton_td=dialog.findViewById(R.id.updateProfileradioButton_td);
        final RadioButton updateProfileradioButton_uv=dialog.findViewById(R.id.updateProfileradioButton_uv);
        LinearLayout nhaTuyenDungLayout=dialog.findViewById(R.id.nhaTuyenDungLayout);
        final EditText updateProfileDateOfBirthtd=dialog.findViewById(R.id.updateProfileDateOfBirthtd);
        final RadioButton updateProfileradioButton_sexNamtd=dialog.findViewById(R.id.updateProfileradioButton_sexNamtd);
        RadioButton updateProfileradioButtonsex_Nutd=dialog.findViewById(R.id.updateProfileradioButtonsex_Nutd);
        final EditText updateProfilecompanyName=dialog.findViewById(R.id.updateProfilecompanyName);
        final EditText updateProfilecompanyAddress=dialog.findViewById(R.id.updateProfilecompanyAddress);
        final EditText updateProfilecompanyPhone=dialog.findViewById(R.id.updateProfilecompanyPhone);
        final EditText updateProfilecompanyEmail= dialog.findViewById(R.id.updateProfilecompanyEmail);
        // UV
        LinearLayout ungVienLayout=dialog.findViewById(R.id.ungVienLayout);
        final EditText updateProfileDateOfBirthuv=dialog.findViewById(R.id.updateProfileDateOfBirthuv);
        RadioButton updateProfileradioButton_sexNamuv=dialog.findViewById(R.id.updateProfileradioButton_sexNamuv);
        RadioButton updateProfileradioButtonsex_Nuuv=dialog.findViewById(R.id.updateProfileradioButtonsex_Nuuv);
         updateProfilespnListOfJob=dialog.findViewById(R.id.updateProfilespnListOfJob);
        final EditText updateProfileAddressuv=dialog.findViewById(R.id.updateProfileAddressuv);
        final EditText updateProfilePhoneuv=dialog.findViewById(R.id.updateProfilePhoneuv);
        final EditText updateProfileFromTimeuv=dialog.findViewById(R.id.updateProfileFromTimeuv);
        final EditText updateProfileToTimeuv=dialog.findViewById(R.id.updateProfileToTimeuv);

        if(AccountData.userLogin!=null){
            // TD
            if(AccountData.userLogin.getAccountName()!=null){
                updateProfileName.setText(AccountData.userLogin.getAccountName());
            }
            if(AccountData.userLogin.getEmail()!=null){
                updateProfileEmail.setText(AccountData.userLogin.getEmail());
            }
            if(AccountData.userLogin.getRole()==2){
                updateProfileradioButton_td.setChecked(true);
                ungVienLayout.setVisibility(View.GONE);
                nhaTuyenDungLayout.setVisibility(View.VISIBLE);
                if(AccountData.userLogin.getDateOfBirth()!=null){
                    updateProfileDateOfBirthtd.setText(AccountData.userLogin.getDateOfBirth());
                }
                if(AccountData.userLogin.getSex().equals("Nam")){
                    updateProfileradioButton_sexNamtd.setChecked(true);
                }else{
                    updateProfileradioButtonsex_Nutd.setChecked(true);
                }
                if(AccountData.userLogin.getCompanyName()!=null){
                    updateProfilecompanyName.setText(AccountData.userLogin.getCompanyName());
                }
                if(AccountData.userLogin.getCompanyAddress()!=null){
                    updateProfilecompanyAddress.setText(AccountData.userLogin.getCompanyAddress());
                }
                if(AccountData.userLogin.getCompanyPhone()!=null){
                    updateProfilecompanyPhone.setText(AccountData.userLogin.getCompanyPhone());
                }

            }else{
                // UV
                updateProfileradioButton_uv.setChecked(true);
                ungVienLayout.setVisibility(View.VISIBLE);
                nhaTuyenDungLayout.setVisibility(View.GONE);
                if(AccountData.userLogin.getDateOfBirth()!=null){
                    updateProfileDateOfBirthuv.setText(AccountData.userLogin.getDateOfBirth());
                }
                if(AccountData.userLogin.getDateOfBirth()!=null){
                    updateProfileDateOfBirthuv.setText(AccountData.userLogin.getDateOfBirth());
                }
                if(AccountData.userLogin.getSex().equals("Nam")){
                    updateProfileradioButton_sexNamuv.setChecked(true);
                }else{
                    updateProfileradioButtonsex_Nuuv.setChecked(true);
                }
                if(AccountData.userLogin.getAddress()!=null){
                    updateProfileAddressuv.setText(AccountData.userLogin.getAddress());
                }
                if(AccountData.userLogin.getPhone()!=null){
                    updateProfilePhoneuv.setText(AccountData.userLogin.getPhone());
                }
                if(AccountData.userLogin.getPhone()!=null){
                    updateProfilePhoneuv.setText(AccountData.userLogin.getPhone());
                }
                updateProfileFromTimeuv.setText(AccountData.userLogin.getDtFrom()+"");
                updateProfileToTimeuv.setText(AccountData.userLogin.getDtTo()+"");
                CategoryData.setUpCategoryData(new IEvenItem() {
                    @Override
                    public void callEvent() {
                        setTitlePostAdapter(CategoryData.categoryList);
                    }
                });
            }

        }

        // end bindding control dialog

        // set event control dialog
        updateProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //progressDoalogupdate.show();
               // Toast.makeText(ProfileActivity.this, "Biết rồi, tôi đang làm, đừng click nữa", Toast.LENGTH_SHORT).show();
                final Account account = new Account();
                account.setAccountName(AccountData.firebaseUser.getDisplayName());
                account.setEmail(AccountData.firebaseUser.getEmail());
                if (updateProfileradioButton_uv.isChecked()) {
                    account.setPhone(updateProfilePhoneuv.getText().toString());
                    account.setAddress(updateProfileAddressuv.getText().toString());
                    account.setDateOfBirth(updateProfileDateOfBirthuv.getText().toString());
                    account.setSex(updateProfileradioButton_sexNamtd.isChecked() ? "Nam" : "Nữ");
                    account.setCategory(new Category(spinnerMap.get(updateProfilespnListOfJob.getSelectedItemPosition()), updateProfilespnListOfJob.getSelectedItem().toString()));
                    account.setDtTo(Integer.parseInt(updateProfileFromTimeuv.getText().toString()));
                    account.setDtFrom(Integer.parseInt(updateProfileToTimeuv.getText().toString()));

                } else {
                    account.setSex(updateProfileradioButton_sexNamtd.isChecked() ? "Nam" : "Nữ");
                    account.setDateOfBirth(updateProfileDateOfBirthtd.getText().toString());
                    account.setCompanyEmail(updateProfilecompanyEmail.getText().toString());
                    account.setCompanyName(updateProfilecompanyName.getText().toString());
                    account.setCompanyPhone(updateProfilecompanyPhone.getText().toString());
                    account.setCompanyAddress(updateProfilecompanyAddress.getText().toString());
                }
                AccountData.UpdateAccount(account, new IEventData() {
                    @Override
                    public void EventSuccess() {
                    //    progressDoalogupdate.dismiss();
                        AccountData.userLogin = account;
                        dialog.dismiss();
                    }
                    @Override
                    public void EventFail(String message) {
                        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                      //  progressDoalogupdate.dismiss();
                        dialog.dismiss();
                    }
                });
                //  dialog.dismiss();
            }
        });
        profileReturnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        // end event control dialog
        dialog.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*0.95),(int)(getResources().getDisplayMetrics().heightPixels*0.95));
        dialog.setTitle("Cập nhập thông tin.");
        dialog.setCancelable(true);
        dialog.show();
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
            updateProfilespnListOfJob.setAdapter(listOfPostAdapter);

        }
    }

    /**
     * Khi click vào avatar thì bắn intent mở trình xem ảnh mặc định để chọn ảnh
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        progressDoalog.show();
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1994 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(ProfileActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                InputStream inputStream = ProfileActivity.this.getContentResolver().openInputStream(data.getData());
                Bitmap imgBitmap = BitmapFactory.decodeStream(inputStream);
                imgBitmap = ImageUtils.cropToSquare(imgBitmap);
                InputStream is = ImageUtils.convertBitmapToInputStream(imgBitmap);
                final Bitmap liteImage = ImageUtils.makeImageLite(is,
                        imgBitmap.getWidth(), imgBitmap.getHeight(),
                        ImageUtils.AVATAR_WIDTH, ImageUtils.AVATAR_HEIGHT);
                         String imageBase64 = ImageUtils.encodeBase64(liteImage);
                      final   Account account= AccountData.userLogin;
                        account.setAvata(imageBase64);
                        FirebaseDatabase.getInstance().getReference().child("Avata")
                                .child(AccountData.userLogin.getKey())
                               .setValue(new Image(imageBase64))
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if(task.isSuccessful()){
                                           progressDoalog.dismiss();
                                           userAvata.setImageDrawable(ImageUtils.roundedImage(ProfileActivity.this, liteImage));
                                           AccountData.userLogin = account;
                                       }
                                   }
                               })
                               .addOnFailureListener(new OnFailureListener() {
                                   @Override
                                   public void onFailure(@NonNull Exception e) {
                                       progressDoalog.dismiss();
                                       Toast.makeText(ProfileActivity.this, "Update thất bại.", Toast.LENGTH_SHORT).show();
                                   }
                               });
                Toast.makeText(this, "Update thành công!", Toast.LENGTH_SHORT).show();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void Logout(){
        Intent i = new Intent(ProfileActivity.this, MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        // noinspection SimplifiableIfStatement
        switch (item.getItemId()) {
            case android.R.id.home: {
                Logout();
                return true;
            }
            default: {
                return super.onOptionsItemSelected(item);

            }
        }
    }
    private void addControl() {
        toolbar = findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(ic_chevron_left_black_24dp);
        toolbar.setTitle("Thông tin cá nhân ");
        toolbar.setTitleMargin(2, 0, 0, 2);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        profileTimeWorkLayout=findViewById(R.id.profileTimeWorkLayout);
        profileCompanyLayout=findViewById(R.id.profileCompanyLayout);
        txtuser_profile_name=findViewById(R.id.user_profile_name);
        txtuser_profile_short_bio=findViewById(R.id.user_profile_short_bio);
        edit_profile_btn=findViewById(R.id.edit_profile_btn);
        if(AccountData.userLogin!=null && AccountData.userLogin.getRole()==1){
            profileCompanyLayout.setVisibility(View.GONE);
            profileTimeWorkLayout.setVisibility(View.VISIBLE);
            dtFrom=findViewById(R.id.profileUserTimeStart);
            dtTo=findViewById(R.id.profileUserTimeEnd);
            dtFrom.setText(AccountData.userLogin.getDtFrom()+"h");
            dtTo.setText(AccountData.userLogin.getDtTo()+"h");
        }else{
            if(AccountData.userLogin!=null && AccountData.userLogin.getRole()==2){
                profileCompanyLayout.setVisibility(View.VISIBLE);
                profileTimeWorkLayout.setVisibility(View.GONE);
                companyName=findViewById(R.id.profileUsercompanyName);
                companyAddress =findViewById(R.id.profileUsercompanyAddress);
                companyPhone=findViewById(R.id.profileUsercompanyPhone);
                companyEmail=findViewById(R.id.profileUsercompanyEmail);
                companyIntro=findViewById(R.id.profileUsercompanyIntro);
                companyName.setText(AccountData.userLogin.getCompanyName());
                companyAddress.setText(AccountData.userLogin.getCompanyAddress());
                companyPhone.setText(AccountData.userLogin.getCompanyPhone());
                companyEmail.setText(AccountData.userLogin.getCompanyEmail());
                companyIntro.setText(AccountData.userLogin.getCompanyIntro());
            }
        }
        userName=findViewById(R.id.profileUserName);
        userEmail=findViewById(R.id.profileUserEmail);
        userAvata = findViewById(R.id.userAvata);
        sex=findViewById(R.id.profileUserSex);
        dateOfBirth=findViewById(R.id.profileUserBirthDay);
         address=findViewById(R.id.profileUserAddress);
         phone = findViewById(R.id.profileUserPhone);

        if(AccountData.userLogin!=null){
            userName.setText(AccountData.userLogin.getAccountName());
            userEmail.setText(AccountData.userLogin.getEmail());
            txtuser_profile_name.setText(AccountData.userLogin.getAccountName());
            txtuser_profile_short_bio.setText(AccountData.userLogin.getEmail());
            sex.setText(AccountData.userLogin.getSex());
            dateOfBirth.setText(AccountData.userLogin.getDateOfBirth());
            address.setText(AccountData.userLogin.getAddress());
            phone.setText(AccountData.userLogin.getPhone());
            if(AccountData.userLogin.getAvata()!=null && AccountData.userLogin.getAvata()!=""){
                ImageData.binDingImageControl(this, userAvata, AccountData.userLogin.getAvata(), new IEventData() {
                    @Override
                    public void EventSuccess() {}
                    @Override
                    public void EventFail(String message) {
                        Toast.makeText(ProfileActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }


    }
}
