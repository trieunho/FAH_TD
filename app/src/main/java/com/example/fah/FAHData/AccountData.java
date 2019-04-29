package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AccountData {
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static  Account userLogin = null;

   public static  void  setUpAccountData(){
       firebaseAuth = FirebaseAuth.getInstance();
       firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
       userLogin = new Account();
    }

    public static void InsertAccount(Account account) {
        FAHQuery.InsertData(account);
    }

    public static String InsertAccountGetKey(Account account) {
        return FAHQuery.InsertDataGetKey(account);
    }

    public boolean InsertListAccount() {
        return true;
    }

    public static void UpdateAccount(Account account) {
        FAHQuery.UpdateData(account);
    }

    public boolean GetAccount() {
        return true;
    }

    public boolean GetListAccount() {
        return true;
    }

    public boolean DeleteAccount() {
        return true;
    }

    public static Image getImageSourceAvata() {
        final Image image = new Image();
        if (userLogin.getKey() != null) {
            FirebaseDatabase.getInstance().getReference().child("Avata").child(userLogin.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                       Image img =dataSnapshot.getValue(Image.class);
                       if(img!=null){
                          image.setSource(img.getSource());
                       }
                    }

                }
                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
        return image;
    }

}
