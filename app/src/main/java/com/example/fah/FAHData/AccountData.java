package com.example.fah.FAHData;

import android.support.annotation.NonNull;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.example.fah.FAHModel.Models.IEvenItem;
import com.example.fah.FAHModel.Models.Image;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONException;
import org.json.JSONObject;

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
        return FAHQuery.InsertDataGetKey(account);
    }

    public boolean InsertListAccount() {
        return true;
    }

    public static void UpdateAccount(Account account) {
        FAHQuery.UpdateData(account);
    }

    public static void GetAccount(final IEvenItem event) throws Exception {
        final Account account = new Account();
        //  Toast.makeText(LoginActivity.this, "email="+AccountData.firebaseUser.getEmail(), Toast.LENGTH_SHORT).show();
        AccountData.firebaseUser = AccountData.firebaseAuth.getCurrentUser();
        FirebaseDatabase.getInstance().getReference().child("Account")
                .orderByChild("email")
                .equalTo(AccountData.firebaseUser.getEmail()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                JSONObject obj = null;
                try {
                    obj = new JSONObject(dataSnapshot.getValue().toString());
                    JSONObject arr = obj.getJSONObject(obj.names().get(0).toString());
                    account.setAccountID(!arr.isNull("accountID") ? arr.getString("accountID") : null);
                    account.setAccountName(!arr.isNull("accountName") ? arr.getString("accountName") : null);
                    account.setSex(!arr.isNull("sex") ? arr.getString("sex") : null);
                    account.setDateOfBirth(!arr.isNull("dateOfBirth") ? arr.getString("dateOfBirth") : null);
                    account.setAddress(!arr.isNull("address") ? arr.getString("address") : null);
                    account.setPhone(!arr.isNull("phone") ? arr.getString("phone") : null);
                    account.setEmail(!arr.isNull("email") ? arr.getString("email") : null);
                    account.setRole(!arr.isNull("role") ? Integer.parseInt(arr.getString("role")) : 0);
                    account.setCompanyName(!arr.isNull("companyName") ? arr.getString("companyName") : null);
                    account.setCompanyAddress(!arr.isNull("companyAddress") ? arr.getString("companyAddress") : null);
                    account.setCompanyPhone(!arr.isNull("companyPhone") ? arr.getString("companyPhone") : null);
                    account.setCompanyEmail(!arr.isNull("companyEmail") ? arr.getString("companyEmail") : null);
                    account.setCompanyIntro(!arr.isNull("companyIntro") ? arr.getString("companyIntro") : null);
                    account.setCoin(!arr.isNull("coin") ? Integer.parseInt(arr.getString("coin")) : 0);
                    account.setStatusBlock(!arr.isNull("statusBlock") ? Integer.parseInt(arr.getString("statusBlock")) : 0);
                    account.setLogin(true);
                    account.setStatusBlock(!arr.isNull("statusSendInvation") ? Integer.parseInt(arr.getString("statusSendInvation")) : null);
                    account.setKey(!arr.isNull("key") ? arr.getString("key") : null);
                    if (!arr.isNull("dtFrom")) {
                        try {
                            account.setDtFrom(Integer.parseInt(arr.getString("dtFrom")));
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                    if (!arr.isNull("dtTo")) {
                        try {
                            account.setDtTo(Integer.parseInt(arr.getString("dtTo")));
                        } catch (Exception e) {
                            throw e;
                        }
                    }
                    AccountData.userLogin = account;
                    event.callEvent();
                } catch (JSONException e) {
                    e.printStackTrace();
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

    public static Image getImageSourceAvata() {
        final Image image = new Image();
        if (userLogin != null && userLogin.getKey() != null) {
            FirebaseDatabase.getInstance().getReference().child("Avata").child(userLogin.getKey()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot != null) {
                        Image img = dataSnapshot.getValue(Image.class);
                        if (img != null) {
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
