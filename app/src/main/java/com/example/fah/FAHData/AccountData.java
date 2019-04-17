package com.example.fah.FAHData;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class AccountData {
    public static   FirebaseAuth firebaseAuth;
    public static   FirebaseUser firebaseUser;

    public AccountData() {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    public FirebaseAuth getFirebaseAuth() {
        return firebaseAuth;
    }

    public void setFirebaseAuth(FirebaseAuth firebaseAuth) {
        this.firebaseAuth = firebaseAuth;
    }

    public static FirebaseUser getUserLogin() {
        return firebaseUser;
    }

    public  boolean InsertAccount(){
        return true;
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
