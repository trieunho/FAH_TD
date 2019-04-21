package com.example.fah.FAHData;

import com.example.fah.FAHCommon.FAHDatabase.FAHQuery;
import com.example.fah.FAHModel.Models.Account;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountData {
    public static   FirebaseAuth firebaseAuth;
    public static   FirebaseUser firebaseUser;
    public   Account userLogin;

    public  void setUserLogin(Account userLogin) {
        userLogin = userLogin;
    }

    public FirebaseUser getfirebaseUser() {
        return firebaseUser;
    }

    public Account getUserLogin() {
        return userLogin;
    }

    public AccountData() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        userLogin = new Account();

    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }



    public  void InsertAccount(Account account){
         FAHQuery.InsertData(account);
    }

    public  boolean InsertListAccount(){
        return true;
    }

    public  boolean UpdateAccount(){
        return true;
    }

    public  boolean GetAccount(){
        return true;
    }

    public  boolean GetListAccount(){
        return true;
    }
    public  boolean DeleteAccount(){
        return true;
    }
}
