package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.IEventData;
import com.example.fah.FAHModel.Models.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AccountData {
    public static FirebaseAuth firebaseAuth;
    public static FirebaseUser firebaseUser;
    public static Account userLogin = null;

    public static void setUpAccountData() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userLogin = new Account();
    }

    public static void InsertAccount(Account account) {
        FAHQuery.InsertData(account);
    }

    public static String InsertAccountGetKey(Account account) {
        return FAHQuery.InsertData(account);
    }

    public boolean InsertListAccount() {
        return true;
    }

    public static void UpdateAccount(Account account) {
        FAHQuery.UpdateData(account);
    }

    public static void GetAccount(final IEventData event) throws Exception {
        final Account account = new Account();
        AccountData.firebaseUser = AccountData.firebaseAuth.getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Account")
                .orderByChild("email")
                .equalTo(AccountData.firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                try {
                    ArrayList<Account> listAcount = (ArrayList<Account>) FAHQuery.GetDataObject(dataSnapshot, new Account());
                    AccountData.userLogin = listAcount.get(0);
                    setImageSourceAvata(new IEventData() {
                        @Override
                        public void EventSuccess() {
                            event.EventSuccess();
                        }

                        @Override
                        public void EventFail() {
                            event.EventSuccess();
                        }
                    });
                } catch (Exception e) {
                    throw e;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public boolean GetListAccount() {
        return true;
    }

    public boolean DeleteAccount() {
        return true;
    }

    private static   Image setImageSourceAvata(final IEventData eventData) {
        final Image image = new Image();
        if (userLogin != null && userLogin.getKey() != null) {
            FirebaseDatabase.getInstance().getReference().child("Avata").child(userLogin.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        Image img = dataSnapshot.getValue(Image.class);
                        if (img != null) {
                            AccountData.userLogin.setAvata(img.getSource());
                        }
                        eventData.EventSuccess();
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
