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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.CommonUtils.ImageUtils;
import com.example.fah.FAHData.AccountData;
import com.example.fah.FAHData.ImageData;
import com.example.fah.FAHModel.Models.Account;
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

import static com.example.fah.R.drawable.ic_chevron_left_black_24dp;

public class ProfileActivity extends AppCompatActivity {
    TextView userName,userEmail,
            sex,dateOfBirth,address,phone,
            companyName,companyAddress,companyPhone
            ,companyEmail,
            companyIntro,dtFrom,dtTo;
    ImageView userAvata,edit_profile_btn;
    ProgressDialog progressDoalog;
    LinearLayout profileCompanyLayout,profileTimeWorkLayout;
    Toolbar toolbar;
    TextView txtuser_profile_name,txtuser_profile_short_bio;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_activity);
        progressDoalog = new ProgressDialog(ProfileActivity.this);
        progressDoalog.setMax(100);
        progressDoalog.setMessage("Đang tải....");
        progressDoalog.setTitle("Tải ảnh lên");
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
        TextView profileReturnTextView=dialog.findViewById(R.id.profileReturnTextView);
        profileReturnTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.getWindow().setLayout((int)(getResources().getDisplayMetrics().widthPixels*0.95),(int)(getResources().getDisplayMetrics().heightPixels*0.95));
        dialog.setTitle("Cập nhập thông tin.");
        dialog.setCancelable(true);
        dialog.show();
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
        if(AccountData.userLogin!=null && AccountData.userLogin.getRole()==0){
            profileCompanyLayout.setVisibility(View.GONE);
            profileTimeWorkLayout.setVisibility(View.VISIBLE);
            dtFrom=findViewById(R.id.profileUserTimeStart);
            dtTo=findViewById(R.id.profileUserTimeEnd);
            dtFrom.setText(AccountData.userLogin.getDtFrom()+"h");
            dtTo.setText(AccountData.userLogin.getDtTo()+"h");
        }else{
            if(AccountData.userLogin!=null && AccountData.userLogin.getRole()==1){
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
