package com.example.fah.FAHScreen.User;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.FAHCommon.CommonUtils.ImageUtils;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHScreen.Main.Tab.MainActivity;
import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.FirebaseDatabase;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ProfileActivity extends AppCompatActivity {
    TextView userName,userEmail;
    ImageView userAvata;
    ProgressDialog progressDoalog;

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
                      final   Account account=MainActivity.userLogin;
                        account.setAvata(imageBase64);

                        FirebaseDatabase.getInstance().getReference().child("Account")
                                .child(MainActivity.userLogin.getKey())
                               .setValue(account)
                               .addOnCompleteListener(new OnCompleteListener<Void>() {
                                   @Override
                                   public void onComplete(@NonNull Task<Void> task) {
                                       if(task.isSuccessful()){
                                           progressDoalog.dismiss();
                                           userAvata.setImageDrawable(ImageUtils.roundedImage(ProfileActivity.this, liteImage));
                                            MainActivity.userLogin = account;
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
    private void addControl() {
        userName=findViewById(R.id.userName);
        userEmail=findViewById(R.id.userEmail);
        userAvata=findViewById(R.id.userAvata);
        userName.setText(MainActivity.userLogin.getAccountName());
        userEmail.setText(MainActivity.userLogin.getEmail());

    }
}
