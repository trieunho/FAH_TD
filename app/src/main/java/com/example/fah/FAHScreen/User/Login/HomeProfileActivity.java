package com.example.fah.FAHScreen.User.Login;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fah.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class HomeProfileActivity extends AppCompatActivity {

    private DatabaseReference userDB;
    TextView userInfoEmail;
    ImageView userAvata;
    Button signOutBtn;
    String avt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_demo);
        userDB = FirebaseDatabase.getInstance().getReference().child("userThanhDC11").child("avt");
       userInfoEmail = (TextView) findViewById(R.id.userNmaeTextView);
        userAvata=findViewById(R.id.avata);
        signOutBtn = (Button) findViewById(R.id.signOutButton);
        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(HomeProfileActivity.this, LoginActivity.class);
                Toast.makeText(getApplicationContext(), "Signing Out", Toast.LENGTH_SHORT).show();
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);
                finish();

            }
        });
        userAvata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(HomeProfileActivity.this)
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
        accessUserInformation();
    }
    /**
     * Khi click vào avatar thì bắn intent mở trình xem ảnh mặc định để chọn ảnh
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1994 && resultCode == Activity.RESULT_OK) {
            if (data == null) {
                Toast.makeText(HomeProfileActivity.this, "Có lỗi xảy ra, vui lòng thử lại", Toast.LENGTH_LONG).show();
                return;
            }
            try {
                InputStream inputStream = HomeProfileActivity.this.getContentResolver().openInputStream(data.getData());

                Bitmap imgBitmap = BitmapFactory.decodeStream(inputStream);
                imgBitmap = ImageUtils.cropToSquare(imgBitmap);
                InputStream is = ImageUtils.convertBitmapToInputStream(imgBitmap);
                final Bitmap liteImage = ImageUtils.makeImageLite(is,
                        imgBitmap.getWidth(), imgBitmap.getHeight(),
                        ImageUtils.AVATAR_WIDTH, ImageUtils.AVATAR_HEIGHT);

                String imageBase64 = ImageUtils.encodeBase64(liteImage);
                avt = imageBase64;



                userDB.child("avata").setValue(imageBase64)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    userAvata.setImageDrawable(ImageUtils.roundedImage(HomeProfileActivity.this, liteImage));
                                }
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                                Toast.makeText(HomeProfileActivity.this, "cap nhap that bai", Toast.LENGTH_SHORT).show();

                            }
                        });
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }


    private void setImageAvatar(Context context, String imgBase64){
        try {
            Resources res = getResources();
            //Nếu chưa có avatar thì để hình mặc định
            Bitmap src;
            if (imgBase64.equals("default")) {
                src = BitmapFactory.decodeResource(res, R.drawable.meninblack);
            } else {
                byte[] decodedString = Base64.decode(imgBase64, Base64.DEFAULT);
                src = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
            }

            userAvata.setImageDrawable(ImageUtils.roundedImage(context, src));
        }catch (Exception e){
        }
    }

    public void accessUserInformation(){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                    .setDisplayName("Duong Cong Thanh")
                    .setPhotoUri(Uri.parse("https://drive.google.com/open?id=1aG8_iHjGqF1pSJiE4cy6_uUZOpHU9bxc"))
                    .build();

            user.updateProfile(profileUpdates)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                              //  Log.d(TAG, "User profile updated.");
                                Toast.makeText(HomeProfileActivity.this, "Dã update", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();
            if(photoUrl!=null){
                setImageAvatar(this,user.getPhotoUrl().toString());
            }
            Toast.makeText(this, "ULR:"+ user.getPhotoUrl(), Toast.LENGTH_SHORT).show();
            Toast.makeText(HomeProfileActivity.this, " user.getDisplayName()"+ user.getDisplayName(), Toast.LENGTH_SHORT).show();

            userInfoEmail.setText(email);

            // Check if user's email is verified
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getIdToken() instead.
            String uid = user.getUid();
        }


    }



    public static class ImageUtils {

        public static final int AVATAR_WIDTH = 128;
        public static final int AVATAR_HEIGHT = 128;

        /**
         * Bo tròn ảnh avatar
         * @param context
         * @param src ảnh dạng bitmap
         * @return RoundedBitmapDrawable là đầu vào cho hàm setImageDrawable()
         */
        public static RoundedBitmapDrawable roundedImage(Context context, Bitmap src){
            /*Bo tròn avatar*/
            Resources res = context.getResources();
            RoundedBitmapDrawable dr =
                    RoundedBitmapDrawableFactory.create(res, src);
            dr.setCornerRadius(Math.max(src.getWidth(), src.getHeight()) / 2.0f);

            return dr;
        }

        /**
         * Đối với ảnh hình chữ nhật thì cần cắt ảnh theo hình vuông và lấy phần tâm
         * ảnh để khi đặt làm avatar sẽ không bị méo
         * @param srcBmp
         * @return
         */
        public static Bitmap cropToSquare(Bitmap srcBmp){
            Bitmap dstBmp = null;
            if (srcBmp.getWidth() >= srcBmp.getHeight()){

                dstBmp = Bitmap.createBitmap(
                        srcBmp,
                        srcBmp.getWidth()/2 - srcBmp.getHeight()/2,
                        0,
                        srcBmp.getHeight(),
                        srcBmp.getHeight()
                );

            }else{
                dstBmp = Bitmap.createBitmap(
                        srcBmp,
                        0,
                        srcBmp.getHeight()/2 - srcBmp.getWidth()/2,
                        srcBmp.getWidth(),
                        srcBmp.getWidth()
                );
            }

            return dstBmp;
        }

        /**
         * Convert ảnh dạng bitmap ra String base64
         * @param imgBitmap
         * @return
         */
        public static String encodeBase64(Bitmap imgBitmap){
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            imgBitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            return Base64.encodeToString(byteArray, Base64.DEFAULT);
        }

        /**
         * Làm giảm số điểm ảnh xuống để tránh lỗi Firebase Database OutOfMemory
         * @param is anh dau vao
         * @param reqWidth kích thước chiều rộng sau khi giảm
         * @param reqHeight kích thước chiều cao sau khi giảm
         * @return
         */
        public static Bitmap makeImageLite(InputStream is, int width, int height,
                                           int reqWidth, int reqHeight) {
            int inSampleSize = 1;

            if (height > reqHeight || width > reqWidth) {
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }

            // Calculate inSampleSize
            final BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = inSampleSize;

            // Decode bitmap with inSampleSize set
            options.inJustDecodeBounds = false;
            return BitmapFactory.decodeStream(is, null, options);
        }


        public static InputStream convertBitmapToInputStream(Bitmap bitmap){
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 0 /*ignored for PNG*/, bos);
            byte[] bitmapdata = bos.toByteArray();
            ByteArrayInputStream bs = new ByteArrayInputStream(bitmapdata);
            return bs;
        }

    }
}

